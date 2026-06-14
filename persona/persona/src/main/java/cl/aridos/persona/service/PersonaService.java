package cl.aridos.persona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.aridos.persona.dto.PersonaDTO;
import cl.aridos.persona.model.Persona;
import cl.aridos.persona.repository.PersonaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonaService {
    @Autowired
    private PersonaRepository pr;
    
    public List<Persona> listarPersonas(){
        return pr.findAll();
    }
    public Persona buscarPorRut(String rut){
        return pr.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Rut no encontrado."));
    }
    public Persona guardarPersona(Persona persona){
        return pr.save(persona);
    }
    public boolean eliminarPersona(String rut){
        Persona p = buscarPorRut(rut);
        if (p!= null) {
            return eliminarPersona(rut);
        }
        return false;
    }
    public Persona actualizarPersona(String rut, Persona persona){
        Persona p = buscarPorRut(rut);
        if (p!= null) {
            p.setRut(rut);
            p.setPNombre(persona.getPNombre());
            p.setSNombre(persona.getSNombre());
            p.setApPaterno(persona.getApPaterno());
            p.setApMaterno(persona.getApMaterno());
            return pr.save(p);
        }
        return null;
    }


    public PersonaDTO buscarPersonaDTO(String rut){
        Persona p = buscarPorRut(rut);
        //return new PagoDTO(p.getIdPago()); esto lo hace la IA que también está bien
        PersonaDTO dto = new PersonaDTO();
        dto.setRutPersona(p.getRut());

        return dto;
    }
}
