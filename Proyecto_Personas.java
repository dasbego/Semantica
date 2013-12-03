/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_personas;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec; 
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution; 
import com.hp.hpl.jena.query.QueryExecutionFactory; 
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter; 
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 *
 * @author bego
 */
public class Proyecto_Personas {
    public static void main(String[] args) {
        OntModel ontmo = ModelFactory.createOntologyModel();
        try{
            ontmo.getDocumentManager().addAltEntry("http://www.semanticweb.org/bego/ontologies/2013/8/persona2.owl", "file:/Users/bego/Documents/Tec de monterrey/9º Semestre/Web Semántica/persona2.owl");
            ontmo.read("http://www.semanticweb.org/bego/ontologies/2013/8/persona2.owl");
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        
        // Imprime los individuals con sus propiedades
        //printIndividuals(ontmo);
        
        
        
    }
    
    public static void printIndividuals(OntModel ontmo){
        ExtendedIterator it = ontmo.listIndividuals();
        while (it.hasNext()) {
            Individual c = (Individual) it.next();
            System.out.println("-Individual: " + c.getLocalName());
            StmtIterator props = c.listProperties();
            while(props.hasNext()){
                Statement ap = props.next();
                String propName = getProp(ap);
                if(!propName.contentEquals("")){
                    System.out.println(propName);
                    System.out.println("\t-Prop: "+ap.getObject());
                }
            }
        }
    }
    
    public static String getProp(Statement ap){
        String qw = ap.getPredicate().toString();
        if(qw.contains("untitled-ontology-7")){
            String we[] = qw.split("untitled-ontology-7");
            //System.out.println(we[1]);
            return we[1];
        }else{
            return "";
        }
    }
}
