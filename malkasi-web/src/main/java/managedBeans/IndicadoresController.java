/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Academico;
import entities.Categoria;
import entities.Proyecto;
import entities.Publicacion;
import entities.TipoFinanciamiento;
import entities.TipoPublicacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author elias
 */
@Named(value = "indicadoresController")
@SessionScoped
public class IndicadoresController implements Serializable {

    private BarChartModel barModel;
    private List<String> years;
    @EJB
    private sessionbeans.PublicacionFacadeLocal publicacionFacade;
    @EJB
    private sessionbeans.AcademicoFacadeLocal academicoFacade;
    @EJB
    private sessionbeans.CategoriaFacadeLocal categoriaFacade;
    @EJB
    private sessionbeans.ProyectoFacadeLocal proyectoFacade;
    private TipoPublicacion tipoPublicacionSeleccionada;
    private Academico academicoSeleccionado;
    List<Publicacion> todasPublicaciones = null;
    List<Proyecto> todosProyectos = null;
    private int agnoInicial;
    private int agnoFinal;
    private ArrayList<Integer> getAgnos;
    private ArrayList<Integer> agnosTabla = null;
    private long categoriaElegida;
    private ArrayList<Academico> academicsCategory;
    private List<Academico> items;
    ArrayList<ArrayList> matriz;
    private List<Categoria> categorias;

    public List<Proyecto> getTodosProyectos() {
        return todosProyectos;
    }

    public void setTodosProyectos(List<Proyecto> todosProyectos) {
        this.todosProyectos = todosProyectos;
    }

    public List<Categoria> listarCategorias() {
        categorias = categoriaFacade.findAll();
        return categorias;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public ArrayList<Academico> getAcademicsCategory() {
        return academicsCategory;
    }

    public void listCategory(long categ) {
        listarCategorias();
        items = academicoFacade.findAll();
        ArrayList<Academico> aux = new ArrayList<Academico>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCategoria() != null) {
                aux.add(items.get(i));
            }
        }
        academicsCategory = null;
        academicsCategory = new ArrayList<Academico>();
        if (categ == 0) {
            academicsCategory.addAll(aux);
        } else {
            System.out.println("Entré al for");
            for (int i = 0; i < aux.size(); i++) {
                if (aux.get(i).getCategoria().getId() == categ) {
                    academicsCategory.add(aux.get(i));
                    System.out.println("Agregué al: " + aux.get(i).getApellidos());
                }
            }
        }
    }

    public void setAcademicsCategory(ArrayList<Academico> academicsCategory) {
        this.academicsCategory = academicsCategory;
    }

    public long getCategoriaElegida() {
        return categoriaElegida;
    }

    public void setCategoriaElegida(long categoriaElegida) {
        this.categoriaElegida = categoriaElegida;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public void llenaryears() {
        years = null;
        years = new ArrayList<String>();
        years.add("Revistas indexadas");
        years.add("Otro tipo de revistas");
        years.add("lakasddfewkfsdkfsdklfksdlklsdkflsdkflsdlfksd");
        System.out.println("Lista 1 es: " + years.get(0));
    }

    public int contadorYears() {
        return years.size();
    }

    public Academico getAcademicoSeleccionado() {
        return academicoSeleccionado;
    }

    public void setAcademicoSeleccionado(Academico academicoSeleccionado) {
        this.academicoSeleccionado = academicoSeleccionado;
    }

    public TipoPublicacion getTipoPublicacionSeleccionada() {
        return tipoPublicacionSeleccionada;
    }

    public void setTipoPublicacionSeleccionada(TipoPublicacion tipoPublicacionSeleccionada) {
        this.tipoPublicacionSeleccionada = tipoPublicacionSeleccionada;
    }

    protected void initializeEmbeddableKey() {
    }

    public TipoPublicacion prepareCreate() {
        tipoPublicacionSeleccionada = new TipoPublicacion();
        initializeEmbeddableKey();
        return tipoPublicacionSeleccionada;
    }

    public List<Publicacion> getTodasPublicaciones() {
        todasPublicaciones = publicacionFacade.findAll();
        return todasPublicaciones;
    }

    public ArrayList llenarCeros() {
        ArrayList<Integer> llenador = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            llenador.add(i, 0);
        }
        return llenador;
    }

    public ArrayList llenarAgnos() {
        ArrayList<Integer> agnos = new ArrayList<Integer>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 7; i++) {
            agnos.add(year - 6 + i);
        }
        return agnos;
    }

    public IndicadoresController() {
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public void setearGrafico() {
        ArrayList<Integer> agnos = new ArrayList<Integer>();
        agnos.addAll(llenarAgnos());
        ArrayList<Integer> cantidadPublicaciones = new ArrayList<Integer>();
        cantidadPublicaciones.addAll(llenarCeros());
        ArrayList<ArrayList<Integer>> ano = new ArrayList<ArrayList<Integer>>();
        ChartSeries publicacion = new ChartSeries();
        ano.add(agnos);
        ano.add(cantidadPublicaciones);
        System.out.println("Tipo Publicacion: " + tipoPublicacionSeleccionada.getNombreTipo());
        todasPublicaciones = publicacionFacade.findAll();
        for (int i = 0; i < todasPublicaciones.size(); i++) {
            for (int j = 0; j < 7; j++) {
                if (ano.get(0).get(j) == todasPublicaciones.get(i).getAgno()) {
                    if (tipoPublicacionSeleccionada.getId() == todasPublicaciones.get(i).getTipoPublicacion().getId()) {
                        int aux = ano.get(1).get(j);
                        ano.get(1).set(j, aux + 1);
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            publicacion.set(ano.get(0).get(i).toString(), ano.get(1).get(i));
        }
        barModel.clear();
        barModel.addSeries(publicacion);
    }

    public void refresh() {
        barModel = null;
        todasPublicaciones = null;
        tipoPublicacionSeleccionada = null;
        academicoSeleccionado = null;
        barModel = new BarChartModel();
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Años");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad Publicaciones");
        ChartSeries A = new ChartSeries();
        for (int i = 0; i < 7; i++) {
            A.set(llenarAgnos().get(i).toString(), 0);
        }
        barModel.addSeries(A);
    }

    public void setearGraficoAcademico() {
        ArrayList<Integer> agnos = new ArrayList<Integer>();
        agnos.addAll(llenarAgnos());
        ArrayList<Integer> cantidadPublicaciones = new ArrayList<Integer>();
        cantidadPublicaciones.addAll(llenarCeros());
        ArrayList<ArrayList<Integer>> ano = new ArrayList<ArrayList<Integer>>();
        ChartSeries publicacion = new ChartSeries();
        ano.add(agnos);
        ano.add(cantidadPublicaciones);
        System.out.println("Academico es: " + academicoSeleccionado.getNombres());
        for (int i = 0; i < academicoSeleccionado.getPublicaciones().size(); i++) {
            for (int j = 0; j < 7; j++) {
                if (ano.get(0).get(j) == academicoSeleccionado.getPublicaciones().get(i).getAgno()) {
                    int aux = ano.get(1).get(j);
                    ano.get(1).set(j, aux + 1);
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            publicacion.set(ano.get(0).get(i).toString(), ano.get(1).get(i));
        }
        barModel.clear();
        barModel.addSeries(publicacion);
    }

    public void setearGraficoAcademicoTipo() {
        ArrayList<Integer> agnos = new ArrayList<Integer>();
        agnos.addAll(llenarAgnos());
        ArrayList<Integer> cantidadPublicaciones = new ArrayList<Integer>();
        cantidadPublicaciones.addAll(llenarCeros());
        ArrayList<ArrayList<Integer>> ano = new ArrayList<ArrayList<Integer>>();
        ChartSeries publicacion = new ChartSeries();
        ano.add(agnos);
        ano.add(cantidadPublicaciones);
        for (int i = 0; i < academicoSeleccionado.getPublicaciones().size(); i++) {
            for (int j = 0; j < 7; j++) {
                if (ano.get(0).get(j) == academicoSeleccionado.getPublicaciones().get(i).getAgno()) {
                    if (academicoSeleccionado.getPublicaciones().get(i).getTipoPublicacion().getId() == tipoPublicacionSeleccionada.getId()) {
                        int aux = ano.get(1).get(j);
                        ano.get(1).set(j, aux + 1);
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            publicacion.set(ano.get(0).get(i).toString(), ano.get(1).get(i));
        }
        barModel.clear();
        barModel.addSeries(publicacion);
    }

    public int cantidadPublicacion(TipoPublicacion tp, Academico a, int amin, int amax) {
        int cant = 0;
        for (int i = 0; i < a.getPublicaciones().size(); i++) {
            if (a.getPublicaciones().get(i).getTipoPublicacion().getId() == tp.getId()) {
                if (a.getPublicaciones().get(i).getAgno() >= amin && a.getPublicaciones().get(i).getAgno() <= amax) {
                    cant++;
                }
            }
        }
        return cant;
    }

    public int cantidadPublicacionTipo(int agno, TipoPublicacion tp) {
        int cant = 0;
        for (int i = 0; i < getTodasPublicaciones().size(); i++) {
            if (todasPublicaciones.get(i).getTipoPublicacion().getId() == tp.getId()) {
                if (todasPublicaciones.get(i).getAgno() == agno) {
                    cant++;
                }
            }
        }
        return cant;
    }

    public void prepararTablaTipoFinanciamiento() {

    }

    public List<Proyecto> getTodasProyectos() {
        todosProyectos = proyectoFacade.findAll();
        return todosProyectos;
    }

    public int cantidadTipoFinanciamiento(int agno, TipoFinanciamiento TF) {
        int cant = 0;
        getTodasProyectos();
        for (int i = 0; i < getTodasProyectos().size(); i++) {
            if (todosProyectos.get(i).getFechaInicioProyecto() != null) {
                if (todosProyectos.get(i).getTipoFinanciamiento().getId() == TF.getId()) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(todosProyectos.get(i).getFechaInicioProyecto());
                    int year = cal.get(Calendar.YEAR);
                    if (year == agno) {
                        cant++;
                    }
                }
            }
        }
        return cant;
    }

    public ArrayList<Integer> getAgnos() {
        ArrayList<Integer> agnos = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            agnos.add(year);
            year--;
        }
        return agnos;
    }

    public int getAgnoInicial() {
        return agnoInicial;
    }

    public void setAgnoInicial(int agnoInicial) {
        this.agnoInicial = agnoInicial;
    }

    public int getAgnoFinal() {
        return agnoFinal;
    }

    public void setAgnoFinal(int agnoFinal) {
        this.agnoFinal = agnoFinal;
    }

    public ArrayList<Integer> getAgnosTabla() {
        agnosTabla = new ArrayList<>();
        for (int i = agnoInicial; i <= agnoFinal; i++) {
            agnosTabla.add(i);
        }
        return agnosTabla;
    }

    public void setAgnosTabla(ArrayList<Integer> agnosTabla) {
        this.agnosTabla = agnosTabla;
    }

    public boolean isPublicacionIndexada(TipoPublicacion tp) {
        if (tp.getNombreTipo().equals("Publicaciones en revistas indexadas")) {
            return true;
        }
        return false;
    }

    public ArrayList<String> tiposIndexacion() {
        ArrayList<String> tI = new ArrayList<>();
        tI.add("indexación ISI");
        tI.add("indexación SCIELO");
        tI.add("indexación SCOPUS");
        tI.add("otro tipo de indexación");
        return tI;
    }

    public int cantidadPublicacionIndex(int agno, String index) {
        int cant = 0;
        for (int i = 0; i < getTodasPublicaciones().size(); i++) {
            boolean ix = tipoIndex(todasPublicaciones.get(i), index);
            if (isPublicacionIndexada(todasPublicaciones.get(i).getTipoPublicacion()) && ix) {
                if (todasPublicaciones.get(i).getAgno() == agno) {
                    cant++;
                }
            }
        }
        return cant;
    }

    public boolean tipoIndex(Publicacion p, String index) {
        if (index.equals("indexación ISI") && p.isIndexIsi()) {
            return true;
        } else if (index.equals("indexación SCIELO") && p.isIdexScielo()) {
            return true;
        } else if (index.equals("indexación SCOPUS") && p.isIndexScopus()) {
            return true;
        } else if (index.equals("otro tipo de indexación") && p.isIdexOther()) {
            return true;
        }
        return false;
    }

    public ArrayList<Academico> academicosPorCategoria(Categoria c) {
        //System.out.println("CAT: " + id);
        //Categoria c = categoriaFacade.find(id);
        ArrayList<Academico> cat = new ArrayList<>();
        items = academicoFacade.findAll();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCategoria().getId() == c.getId()) {
                System.out.println("NAME: " + items.get(i).getApellidos());
                cat.add(items.get(i));
            }
        }
        return cat;
    }
}
