package bpe.auth.service;

import bpe.auth.bean.Credencial;
import bpe.auth.bean.Login;
import bpe.auth.entity.ContaUsuario;
import bpe.auth.exception.CredencialException;
import bpe.auth.exception.TokenException;
import bpe.auth.repository.ContaUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;

@Service
public class AuthService implements Serializable {

    @Autowired
    private ContaUsuarioDao contaUsuarioDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void salvarUsuarioSistema(ContaUsuario conta) {
        conta.senha = bCryptPasswordEncoder.encode(conta.senha);
    }

    public Credencial autenticar(Login login) throws CredencialException, TokenException {
        ContaUsuario conta = contaUsuarioDao.findByLogin(login.login);
        verificarUsuarioSenha(conta, login);
        validarConta(conta);
        return gerarCredenciais(conta);
    }

    public Credencial refresToken(String login) throws CredencialException, TokenException {


        try {
            ContaUsuario conta = contaUsuarioDao.findByLogin(login);
            if(conta == null)
                throw new CredencialException(" Login não encontrado");
            validarConta(conta);
            return gerarCredenciais(conta);
        } catch (CredencialException e) {
            throw new CredencialException("RefreshToken não permitido", e);
        }
    }

    private void verificarUsuarioSenha(ContaUsuario contaUsuario, Login login) throws CredencialException {
        if(contaUsuario == null)
            throw new CredencialException("Login não encontrado");
        if(!(contaUsuario.senha.trim().equals(login.senha.trim())))
            throw new CredencialException("Senha incorreta");
    }

    private void validarConta(ContaUsuario contaUsuario) throws CredencialException {
        if(contaUsuario.contaBloqueada())
            throw new CredencialException("Conta Bloqueada");
        if(contaUsuario.contaExpirada())
            throw new CredencialException("Conta expirada");
    }

    private Credencial gerarCredenciais(ContaUsuario contaUsuario) throws TokenException {
        return Credencial.of()
                .login(contaUsuario.login)
                .token(gerarToken(contaUsuario));
    }

    private Token gerarToken(ContaUsuario contaUsuario) throws TokenException {

        LocalDateTime dataCriacao = LocalDateTime.now();
        long dataCriacaoEpoch = DateUtil.toEpochSegundos(dataCriacao);

        LocalDateTime dataValidade = dataCriacao.plusMinutes(contaUsuario.tempoSessaoMin());
        long dataValidadeEpoch = DateUtil.toEpochSegundos(dataValidade);;

        String rawToken = gerarRawToken(contaUsuario);

        return Token.of()
                .criadoEmEpoch(dataCriacaoEpoch)
                .expiraEmEpoch(dataValidadeEpoch)
                .tempoSessaoMin(contaUsuario.tempoSessaoMin())
                .rawToken(rawToken);

    }

    private String gerarRawToken(ContaUsuario contaUsuario) throws TokenException {
        return JwtFactory.gerarToken(contaUsuario);
    }

}

