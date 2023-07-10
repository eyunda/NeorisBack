package Neoris.back.serviceTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Neoris.back.DTO.CuentaDTO;
import Neoris.back.DTO.CuentaDTOResponse;
import Neoris.back.controller.CuentaController;
import Neoris.back.entity.Cuenta;
import Neoris.back.repository.CuentaRepository;
import Neoris.back.service.Impl.CuentaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import Neoris.back.DTO.CuentaDTOResponse;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CuentaServiceImplTest {

    private CuentaRepository cuentaRepository;
    private CuentaServiceImpl cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    public class CuentaDTOResponseImpl implements CuentaDTOResponse {

        public CuentaDTOResponseImpl(String nombreCuenta) {
            super();
        }

        @Override
        public Integer getId() {
            return null;
        }

        @Override
        public String getNumero_cuenta() {
            return null;
        }

        @Override
        public String getNombre() {
            return null;
        }

        @Override
        public double getSaldo_inicial() {
            return 0;
        }

        @Override
        public String getTipo_cuenta() {
            return null;
        }

        @Override
        public boolean getEstado() {
            return false;
        }
    }



    @BeforeEach
    public void setUp() {
        cuentaRepository = mock(CuentaRepository.class);
        cuentaService = new CuentaServiceImpl(cuentaRepository);
    }

    @Test
    public void testObtenerCuenta() {
        // Arrange
        List<CuentaDTOResponse> cuentas = Arrays.asList(
                new CuentaDTOResponseImpl("Cuenta1"),
                new CuentaDTOResponseImpl("Cuenta2")
        );
        when(cuentaRepository.obtenerCuenta()).thenReturn(cuentas);

        // Act
        List<CuentaDTOResponse> result = cuentaService.obtenerCuenta();

        // Assert
        assertEquals(cuentas, result);
    }

    @Test
    public void testObtenerCuentaPorIdExistente() {
        // Arrange
        Long id = 1L;
        Cuenta cuenta = new Cuenta();
        cuenta.setNombre_cliente("Cuenta1");
        cuenta.setNumero_cuenta("123456789");
        cuenta.setTipo_cuenta("Ahorro");
        cuenta.setSaldo_inicial(100.0);
        cuenta.setEstado(true);

        when(cuentaRepository.findById(id)).thenReturn(Optional.of(cuenta));

        // Act
        CuentaDTO result = cuentaService.obtenerCuentaPorId(id);

        // Assert
        assertNotNull(result);
        assertEquals(cuenta.getNumero_cuenta(), result.getNumero_cuenta());
        assertEquals(cuenta.getTipo_cuenta(), result.getTipo_cuenta());
        assertEquals(cuenta.getSaldo_inicial(), result.getSaldo_inicial());
        assertEquals(cuenta.getNombre_cliente(), result.getNombre_cliente());
        assertEquals(cuenta.isEstado(), result.isEstado());
    }

    @Test
    public void testObtenerCuentaPorIdNoExistente() {
        // Arrange
        Long id = 1L;
        when(cuentaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        CuentaDTO result = cuentaService.obtenerCuentaPorId(id);

        // Assert
        assertNull(result);
    }
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearCuenta() {
        // Datos de ejemplo para la cuenta
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setNombre_cliente("Cliente1");
        cuentaDTO.setNumero_cuenta("123456789");
        cuentaDTO.setTipo_cuenta("Ahorros");
        cuentaDTO.setSaldo_inicial(1000.0);
        cuentaDTO.setEstado(true);

        // Configuración del mock del servicio
        CuentaDTO cuentaCreada = new CuentaDTO();
        cuentaCreada.setId(1L);
        cuentaCreada.setNombre_cliente(cuentaDTO.getNombre_cliente());
        cuentaCreada.setNumero_cuenta(cuentaDTO.getNumero_cuenta());
        cuentaCreada.setTipo_cuenta(cuentaDTO.getTipo_cuenta());
        cuentaCreada.setSaldo_inicial(cuentaDTO.getSaldo_inicial());
        cuentaCreada.setEstado(cuentaDTO.isEstado());

        when(cuentaService.crearCuenta(cuentaDTO)).thenReturn(cuentaCreada);

        // Llamada al método del controlador
        ResponseEntity<CuentaDTO> responseEntity = cuentaController.crearCuenta(cuentaDTO);

        // Verificación de la respuesta y el resultado esperado
        verify(cuentaService, times(1)).crearCuenta(cuentaDTO);
        assertSame(cuentaCreada, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}

