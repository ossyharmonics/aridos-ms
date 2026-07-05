package cl.aridos.empresa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.aridos.empresa.model.Empresa;
import cl.aridos.empresa.repository.EmpresaRepository;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @Test
    void buscarPorRut_cuandoEmpresaExiste_retornaEmpresa() {
        String rut = "12345678-9";
        Empresa empresa = new Empresa();
        empresa.setRutEmpresa(rut);
        empresa.setNombreEmpresa("Test S.A.");

        when(empresaRepository.findByRutEmpresa(rut)).thenReturn(Optional.of(empresa));

        Empresa resultado = empresaService.buscarPorRut(rut);

        assertNotNull(resultado);
        assertEquals(rut, resultado.getRutEmpresa());
        assertEquals("Test S.A.", resultado.getNombreEmpresa());
        verify(empresaRepository).findByRutEmpresa(rut);
    }

    @Test
    void buscarPorRut_cuandoEmpresaNoExiste_lanzaExcepcion() {
        String rut = "99999999-9";

        when(empresaRepository.findByRutEmpresa(rut)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> empresaService.buscarPorRut(rut));
        verify(empresaRepository).findByRutEmpresa(rut);
    }
}
