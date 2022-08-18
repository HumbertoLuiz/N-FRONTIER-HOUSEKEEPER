package com.learning.web.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.core.enums.UserType;
import com.learning.core.models.User;
import com.learning.core.repository.UserRepository;
import com.learning.web.dtos.UserInsertForm;
import com.learning.web.mappers.WebUserMapper;
import com.learning.web.services.WebUserService;

@Service
public class WebUserServiceImp implements WebUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebUserMapper webUserMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserValidator validator;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User insert(UserInsertForm form) {
        //validarConfirmacaoSenha(form);

        var model = webUserMapper.toModel(form);

        //var senhaHash = passwordEncoder.encode(model.getSenha());

        //model.setSenha(senhaHash);
        model.setUserType(UserType.ADMIN);

        //validator.validar(model);

        return userRepository.save(model);
    }

//    public Usuario buscarPorId(Long id) {
//        var mensagem = String.format("Usuário com ID %d não encontrado", id);
//
//        return repository.findById(id)
//            .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
//    }
//
//    public Usuario buscarPorEmail(String email) {
//        var mensagem = String.format("Usuário com email %s não encontrado", email);
//
//        return repository.findByEmail(email)
//            .orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
//    }
//
//    public UsuarioEdicaoForm buscarFormPorId(Long id) {
//        var usuario = buscarPorId(id);
//
//        return mapper.toForm(usuario);
//    }
//
//    public Usuario editar(UsuarioEdicaoForm form, Long id) {
//        var usuario = buscarPorId(id);
//
//        var model = mapper.toModel(form);
//        model.setId(usuario.getId());
//        model.setSenha(usuario.getSenha());
//        model.setTipoUsuario(usuario.getTipoUsuario());
//
//        validator.validar(model);
//
//        return repository.save(model);
//    }
//
//    public void excluirPorId(Long id) {
//        var usuario = buscarPorId(id);
//
//        repository.delete(usuario);
//    }
//
//    public void alterarSenha(AlterarSenhaForm form, String email) {
//        var usuario = buscarPorEmail(email);
//
//        validarConfirmacaoSenha(form);
//
//        var senhaAtual = usuario.getSenha();
//        var senhaAntiga = form.getSenhaAntiga();
//        var senha = form.getSenha();
//
//        if (!passwordEncoder.matches(senhaAntiga, senhaAtual)) {
//            var mensagem = "A senha antiga está incorreta";
//            var fieldError = new FieldError(form.getClass().getName(), "senhaAntiga", senhaAntiga, false, null, null, mensagem);
//
//            throw new SenhaIncorretaException(mensagem, fieldError);
//        }
//
//        var novaSenhaHash = passwordEncoder.encode(senha);
//        usuario.setSenha(novaSenhaHash);
//        repository.save(usuario);
//    }
//
//    private void validarConfirmacaoSenha(IConfirmacaoSenha obj) {
//        var senha = obj.getSenha();
//        var confirmacaoSenha = obj.getConfirmacaoSenha();
//
//        if (!senha.equals(confirmacaoSenha)) {
//            var mensagem = "Os dois campos de senha não conferem";
//            var fieldError = new FieldError(obj.getClass().getName(), "confirmacaoSenha", obj.getConfirmacaoSenha(), false, null, null, mensagem);
//
//            throw new SenhasNaoConferemException(mensagem, fieldError);
//        }
    }