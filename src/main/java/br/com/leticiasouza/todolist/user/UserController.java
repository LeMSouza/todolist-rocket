package br.com.leticiasouza.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Modificador
 * public
 * private
 * protected
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRespository;

    /**
     * String (texto)
     * Interger (int) numeros inteiros
     * Double (double) Números 0.0000
     * Float (float) Números .000     
     * char (A C)
     * Data (data)
     * void (Não tem o retorno do método)
     */
    /*
     * Body
     */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
       var user = this.userRespository.findByUsername(userModel.getUsername());

        if(user != null) {
            // Mensagem de erro
            // Status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

       var userCreated = this.userRespository.save(userModel);
       return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
