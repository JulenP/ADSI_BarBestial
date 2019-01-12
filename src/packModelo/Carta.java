package packModelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Carta implements Comparable<Carta> {
    private Animal animal;
    private EnumColor color;

    public Carta(Animal animal, EnumColor color) {
        this.animal = animal;
        this.color = color;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public EnumColor getColor() {
        return this.color;
    }

    public String getEspecie() {
        return this.animal.getEspecie();
    }

    public int getFuerza() {
        return this.animal.getFuerza();
    }

    public void hacerAnimalada() {
        this.animal.hacerAnimalada();
    }

    @Override
    public int compareTo(Carta o) {
        if (o.getAnimal().getFuerza() <= this.animal.getFuerza()) {
            return -1;
        } else {
            if (o.getAnimal().getFuerza() > this.animal.getFuerza()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    /**
     * devuelve la informacion(especie,color) de la carta en un JSONObject 
     * JSON formato {especie: String,color:String}
     */
	public JSONObject obtenerDatosCarta() {
		JSONObject carta=new JSONObject();
		try {
			carta.put("especie", this.getEspecie());
			carta.put("color", this.getColor());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return carta;
	}
}
