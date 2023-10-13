package br.com.leticiasouza.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

//import br.com.leticiasouza.todolist.user.LocalDataTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
     * 
     * ID
     * Usuário (ID_USUÁRIO)
     * Descrição
     * Título
     * Data de Início
     * Data de Término
     * Prioridade
     * 
     */
@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID ID;
    private String description;

    @Column(length = 50)
    private String title;
    //private LocalDataTime startAt;
    //private LocalDataTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception {
        if(title.length() > 50) {
            throw new Exception("O campo title deve conter o máximo 50 caracteres");
        }
        this.title = title;
    }

}
