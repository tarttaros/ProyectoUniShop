package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;

@Getter @Setter
public class Mail {
    private String username;
    private String to;
    private String subject;
    private String text;
    private File file;
    private Date sendDate;
    public Mail(){}

    public Mail(String username, String to, String subject, String text, File file, Date sendDate) {
        this.username = username;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.file = file;
        this.sendDate = sendDate;
    }
}
