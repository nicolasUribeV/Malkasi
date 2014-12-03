/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Log;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class LogFacadeTest {

    @Mock
    private LogFacade logFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public LogFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        //academicoExternoFacade = new AcademicoExternoFacade();
        //academicoExterno = mock(academicoExterno.getClass());
        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        logFacade = Mockito.mock(LogFacade.class);
        logFacade.em = entityManager;
        // academicoExternoFacade.entityManager = entityManager;
        //academicoExternoFacade.setEm(entityManager); // inject our stubbed entity manager
    }

    @Test
    public void testFindAll() throws Exception {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);// I might have the wrong Calendar constant...
        cal.set(Calendar.MONTH, 8);// -1 as month is zero-based
        cal.set(Calendar.YEAR, 2009);
        Timestamp tstamp = new Timestamp(cal.getTimeInMillis());
        Log log = new Log();
        log.setFecha(tstamp);
        log.setAccion("Editar");
        log.setId(Long.parseLong("1"));
        log.setProfesor("cristobal");
        log.setTitulo("edito");
        
        List<Log> logs = new ArrayList<>();
        logs.add(log);
        List<Log> logsTest = new ArrayList<>();
        logsTest.add(log);
         when(entityManager.createNamedQuery("SELECT a FROM Log a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Log a");

        when(querys.getResultList()).thenReturn(logs);

        when(logFacade.findAll()).thenReturn(logsTest);

        assertEquals(logsTest, logs);
        

    }

}
