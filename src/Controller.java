import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    private Agenda agenda = new Agenda();
    private Persona personaEncontrada;
    private int seleccionBusqueda = 1;//1 Nombre, 2 Teléfono, 3 Correo
    @FXML
    private TextField txtRuta;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private AnchorPane paneBusqueda;
    @FXML
    private AnchorPane paneArchivoExcel;
    @FXML
    private AnchorPane paneBusquedaLista;
    @FXML
    private AnchorPane paneContactoNoEncontrado;
    @FXML
    private AnchorPane paneContacto;
    @FXML
    private JFXRadioButton radNombre;
    @FXML
    private JFXRadioButton radTelefono;
    @FXML
    private JFXRadioButton radCorreo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private Label lblnombreCompleto;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblCorrero;


    public void onApplyButtonClicked(MouseEvent event){
        if (agenda.cargarArchivo(txtRuta.getText())){
            paneBusqueda.setDisable(false);
            paneArchivoExcel.setVisible(false);
            paneContactoNoEncontrado.setVisible(false);
            paneContacto.setVisible(false);
            paneBusquedaLista.setVisible(true);
            radNombre.setSelected(true);
            radCorreo.setSelected(false);
            radTelefono.setSelected(false);
            txtBusqueda.setText("");
        }else {
            System.out.println("No se ha cargado ningún archivo");
            paneBusqueda.setDisable(true);
            paneArchivoExcel.setVisible(true);
            paneContactoNoEncontrado.setVisible(false);
            paneContacto.setVisible(false);
            paneBusquedaLista.setVisible(false);
            radNombre.setSelected(false);
            radCorreo.setSelected(false);
            radTelefono.setSelected(false);
        }
    }

    public void onSelectedRadioNombre(ActionEvent event){
        System.out.println("Busqueda por nombre seleccionada");
        radNombre.setSelected(true);
        radCorreo.setSelected(false);
        radTelefono.setSelected(false);
        seleccionBusqueda = 1;
    }

    public void onSelectedRadioTelefono(ActionEvent event){
        System.out.println("Busqueda por Teléfono seleccionada");
        radCorreo.setSelected(false);
        radNombre.setSelected(false);
        radTelefono.setSelected(true);
        seleccionBusqueda = 2;
    }
    public void onSelectedRadioCorreo(ActionEvent event){
        System.out.println("Busqueda por Correo seleccionada");
        radNombre.setSelected(false);
        radTelefono.setSelected(false);
        radCorreo.setSelected(true);
        seleccionBusqueda = 3;
    }

    public void onSelectedSearchButtonClicked(MouseEvent event){
        System.out.println("Buscando Contacto");

        switch (seleccionBusqueda){
            case 1:
                personaEncontrada = agenda.buscarContactoPorNombre(txtBusqueda.getText());
                break;
            case 2:
                try {
                    personaEncontrada = agenda.buscarContactoPorTelefono((Long.parseLong(txtBusqueda.getText())));
                }catch (Exception exception){
                    System.out.println("Fallo al convertir String a Long");
                }
                break;
            case 3:
                personaEncontrada = null;
                personaEncontrada = agenda.buscarContactoPorCorreo(txtBusqueda.getText());
                break;

        }
        if (personaEncontrada != null){
            lblnombreCompleto.setText(personaEncontrada.getNombre() + " " + personaEncontrada.getaPaterno() + " " + personaEncontrada.getaMaterno());
            lblTelefono.setText(Long.toString(personaEncontrada.getTelefono()));
            lblCorrero.setText(personaEncontrada.getCorreo());
            paneContacto.setVisible(true);
            paneContactoNoEncontrado.setVisible(false);
            paneBusquedaLista.setVisible(false);
            paneArchivoExcel.setVisible(false);
            personaEncontrada = null;
            System.out.println("Contacto encontrado");
        }else{
            paneArchivoExcel.setVisible(false);
            paneBusquedaLista.setVisible(false);
            paneContacto.setVisible(false);
            paneContactoNoEncontrado.setVisible(true);
            personaEncontrada = null;
            System.out.println("Contacto no encontrado");
        }
    }
}
