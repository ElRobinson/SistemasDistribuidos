/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

/**
 *
 * @author luisrobinson
 */
import rmi.Topico;
import crud.Noticia;
import java.util.List;

public class MainBackup {
    
    
    //cria backup em memória
    //Topico tp = new Topico();
    //Noticia nt = new Noticia(tp.nome);
    
    public Topico setTopico(Topico top){
        Topico tp = new Topico();
        
        for (Noticia noticia : top.noticias){
            
        }
        return tp; 
    }
}


