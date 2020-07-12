import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Agenda {

    private Persona[] personas;
    private Persona lOPersona = null;

    public boolean cargarArchivo(String ruta){
        ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
        try {
            FileInputStream archivo = new FileInputStream(ruta);
            XSSFWorkbook libro = new XSSFWorkbook(archivo);
            XSSFSheet hoja = libro.getSheetAt(0);
            Iterator<Row> rowIterator = hoja.iterator();
            Row row;
            while (rowIterator.hasNext()){
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell;
                int columna = 1;
                Persona persona = new Persona();
                while (cellIterator.hasNext()){
                    cell = cellIterator.next();
                    switch (columna){
                        case 1:
                            persona.setNombre(cell.getStringCellValue());
                            break;
                        case 2:
                            persona.setaPaterno(cell.getStringCellValue());
                            break;
                        case 3:
                            persona.setaMaterno(cell.getStringCellValue());
                            break;
                        case 4:
                            persona.setTelefono((long)cell.getNumericCellValue());
                            break;
                        case 5:
                            persona.setCorreo(cell.getStringCellValue());
                            break;
                    }
                    columna++;
                }
                listaPersonas.add(persona);
            }
            libro.close();
            return convertirArreglo(listaPersonas);
        }catch (Exception exception){
            return false;
        }
    }

    public boolean convertirArreglo(ArrayList<Persona> arreglo){
        int contador = arreglo.size();
        this.personas = new Persona[contador];
        for (int i = 0; i < contador; i++){
            this.personas[i] = arreglo.get(i);
        }
        System.out.println("Archivo cargado éxitosamente");
        return true;
    }

    public Persona buscarContactoPorNombre(String pSNombre){
        lOPersona = null;
        if (this.personas.length > 0){
            for (int i = 0; i < this.personas.length; i++){
                if (this.personas[i].getNombre().equals(pSNombre)){
                    lOPersona = this.personas[i];
                }
            }
        }
        return lOPersona;
    }

    public Persona buscarContactoPorTelefono(long plTelefono){
        lOPersona = null;
        if (this.personas.length > 0){
            for (int i = 0; i < this.personas.length; i++){
                if (this.personas[i].getTelefono() == plTelefono){
                    lOPersona = this.personas[i];
                }
            }
        }
        return lOPersona;
    }

    public Persona buscarContactoPorCorreo(String pSCorreo){
        lOPersona = null;
        if (this.personas.length > 0){
            for (int i = 0; i < this.personas.length; i++){
                if (this.personas[i].getCorreo().equals(pSCorreo)){
                    lOPersona = this.personas[i];
                }
            }
        }
        return lOPersona;
    }
}
