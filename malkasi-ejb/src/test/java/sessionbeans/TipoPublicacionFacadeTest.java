/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.TipoPublicacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author cristobal
 */
public class TipoPublicacionFacadeTest {

    @Mock
    private TipoPublicacionFacade tipoPublicacionFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public TipoPublicacionFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        //academicoExternoFacade = new AcademicoExternoFacade();
        //academicoExterno = mock(academicoExterno.getClass());
        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        tipoPublicacionFacade = Mockito.mock(TipoPublicacionFacade.class);
        tipoPublicacionFacade.em = entityManager;
        // academicoExternoFacade.entityManager = entityManager;
        //academicoExternoFacade.setEm(entityManager); // inject our stubbed entity manager
    }

    @Test
    public void testFindAll() throws Exception {

        TipoPublicacion tipoPublicacion = new TipoPublicacion();
        tipoPublicacion.setId(Long.parseLong("1"));
        tipoPublicacion.setNombreTipo("publicacion indexada");

        List<TipoPublicacion> tipoPublicaciones = new ArrayList<>();
        List<TipoPublicacion> tipoPublicacionesTest = new ArrayList<>();
        
        tipoPublicaciones.add(tipoPublicacion);
        tipoPublicacionesTest.add(tipoPublicacion);
        
        when(entityManager.createNamedQuery("SELECT a FROM TipoPublicacion a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM TipoPublicacion a");

        when(querys.getResultList()).thenReturn(tipoPublicaciones);

        when(tipoPublicacionFacade.findAll()).thenReturn(tipoPublicacionesTest);

        assertEquals(tipoPublicacionesTest, tipoPublicaciones);
            
    }

}
