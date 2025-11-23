package main.java.com.espaco.controle.persistencia;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.diziart.no2.objects.filters.ObjectFilters;

import java.util.List;

public class NitritePersistencia {
    
    private final Nitrite db;

    public NitritePersistencia(String nomeArquivo) {
        this.db = Nitrite.builder()
                .filePath(nomeArquivo)
                .openOrCreate();
    }

    //Método genérico para salvar qualquer coisa
    public <T> void salvar(T objeto) {
        ObjectRepository<T> repository = (ObjectRepository<T>) db.getRepository(objeto.getClass());
        repository.update(objeto, true);
    }

    //Método genérico para Listar qualuer coisa
    public <T> List<T> listar(Class<T> classe) {
        return db.getRepository(tipoClasse).find(ObjectFilters.ALL).toList();
    }

    public void fecharDB() {
        if(db != null && !db.isClosed()) {
            db.close();
        }
    }

}
