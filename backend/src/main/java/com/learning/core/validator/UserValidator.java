package com.learning.core.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import com.learning.core.exceptions.UserAlreadyRegisteredException;
import com.learning.core.models.User;
import com.learning.core.repository.UserRepository;


@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(User user) {
        validateEmail(user);
    }

    private void validateEmail(User user) {
        if (userRepository.isEmailJaCadastrado(user)) {
            var message = "User Registered Already exists with this e-mail";
            var fieldError = new FieldError(user.getClass().getName(), "email", user.getEmail(), false, null, null, message);

            throw new UserAlreadyRegisteredException(message, fieldError);
        }

        //validateCpf(user);
    }

//    private void validarCpf(Usuario usuario) {
//        if (repository.isCpfJaCadastrado(usuario)) {
//            var mensagem = "Já existe um usuário cadastrado com esse cpf";
//            var fieldError = new FieldError(usuario.getClass().getName(), "cpf", usuario.getCpf(), false, null, null, mensagem);
//
//            throw new UsuarioJaCadastradoException(mensagem, fieldError);
//        }
//
//        validarChavePix(usuario);
//    }

//    private void validarChavePix(Usuario usuario) {
//        if (repository.isChavePixJaCadastrada(usuario)) {
//            var mensagem = "Já existe um usuário cadastrado com essa chave pix";
//            var fieldError = new FieldError(usuario.getClass().getName(), "chavePix", usuario.getChavePix(), false, null, null, mensagem);
//
//            throw new UsuarioJaCadastradoException(mensagem, fieldError);
//        }
//
//        if (usuario.isDiarista() && usuario.getChavePix() == null) {
//            var mensagem = "Usuário do tipo DIARISTA precisa ter a chave pix";
//            var fieldError = new FieldError(usuario.getClass().getName(), "chavePix", usuario.getChavePix(), false, null, null, mensagem);
//
//            throw new ValidacaoException(mensagem, fieldError);
//        }
//    }

}