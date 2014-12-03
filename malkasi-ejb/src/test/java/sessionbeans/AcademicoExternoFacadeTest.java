/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.AcademicoExterno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author cristobal
 */
@RunWith(MockitoJUnitRunner.class)
public class AcademicoExternoFacadeTest {

    @Mock
    private AcademicoExternoFacade academicoExternoFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public AcademicoExternoFacadeTest(){
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        //academicoExternoFacade = new AcademicoExternoFacade();
        //academicoExterno = mock(academicoExterno.getClass());
        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        academicoExternoFacade = Mockito.mock(AcademicoExternoFacade.class);
        academicoExternoFacade.em = entityManager;
        // academicoExternoFacade.entityManager = entityManager;
        //academicoExternoFacade.setEm(entityManager); // inject our stubbed entity manager
    }

    @Test
    public void testAcademicoExternoFacadeFindAll() {
        AcademicoExterno a1 = new AcademicoExterno();
        AcademicoExterno a2 = new AcademicoExterno();
        AcademicoExterno a3 = new AcademicoExterno();
        a1.setApellidos("Perez");
        a1.setId(Long.parseLong("1"));
        a1.setNombres("Juan");
        a1.setPais("chile");
        a1.setUniversidad("Usach");

        a2.setApellidos("Perez");
        a2.setId(Long.parseLong("2"));
        a2.setNombres("Juan");
        a2.setPais("chile");
        a2.setUniversidad("Usach");

        a3.setApellidos("Perez");
        a3.setId(Long.parseLong("3"));
        a3.setNombres("Juan");
        a3.setPais("chile");
        a3.setUniversidad("Usach");

        List<AcademicoExterno> academicos = new ArrayList<>();

        academicos.add(a1);
        academicos.add(a2);
        academicos.add(a3);

        List<AcademicoExterno> academicosTest = new ArrayList<>();

        academicosTest.add(a1);
        academicosTest.add(a2);
        academicosTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM AcademicoExterno a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM AcademicoExterno a");

        when(querys.getResultList()).thenReturn(academicos);

        when(academicoExternoFacade.findAll()).thenReturn(academicosTest);

        assertEquals(academicosTest, academicos);

    }

    

}
