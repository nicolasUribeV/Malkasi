/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.TipoFinanciamiento;
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
public class TipoFinanciamientoFacadeTest {

    @Mock
    private TipoFinanciamientoFacade tipoFinanciamientoFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public TipoFinanciamientoFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        //academicoExternoFacade = new AcademicoExternoFacade();
        //academicoExterno = mock(academicoExterno.getClass());
        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        tipoFinanciamientoFacade = Mockito.mock(TipoFinanciamientoFacade.class);
        tipoFinanciamientoFacade.em = entityManager;
        // academicoExternoFacade.entityManager = entityManager;
        //academicoExternoFacade.setEm(entityManager); // inject our stubbed entity manager
    }

    @Test
    public void testFindAll() throws Exception {

        TipoFinanciamiento tipoFinanciamiento = new TipoFinanciamiento();
        tipoFinanciamiento.setId(Long.parseLong("1"));
        tipoFinanciamiento.setNombreFinanciamiento("corfo");
        List<TipoFinanciamiento> financiamientos = new ArrayList<>();
        List<TipoFinanciamiento> financiamientosTest = new ArrayList<>();
        
        financiamientos.add(tipoFinanciamiento);
        financiamientosTest.add(tipoFinanciamiento);
        
         when(entityManager.createNamedQuery("SELECT a FROM TipoFinanciamiento a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM TipoFinanciamiento a");

        when(querys.getResultList()).thenReturn(financiamientos);

        when(tipoFinanciamientoFacade.findAll()).thenReturn(financiamientosTest);

        assertEquals(financiamientosTest, financiamientos);
        

    }
}
