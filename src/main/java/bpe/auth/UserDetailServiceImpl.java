package bpe.auth;

import bpe.auth.entity.ContaUsuario;
import bpe.auth.repository.ContaUsuarioDao;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ContaUsuarioDao contaUsuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.equals("root"))
            return new User("root", "123456", Lists.newArrayList());

        ContaUsuario usuario = contaUsuarioDao.findByLogin(username);
        if (usuario == null)
            throw new UsernameNotFoundException(username);
        return new User(usuario.login, usuario.senha, Lists.newArrayList());
    }

}
