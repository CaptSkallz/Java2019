import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

    public List<VelkonocneVajce> vsetkyRozneVajcia() {
      return null; // doprogramuj
    }    
    public List<Ziak> bezVajec() {
      return null; // doprogramuj
    }
    public Ziak najvacsiZberatel() {
      return null; // doprogramuj
    }
    public VelkonocneVajce najcastejsieVajce() {
      return null; // doprogramuj
    }	  
    public VelkonocneVajce najvacsieVajce() {
      return null; // doprogramuj
    }

	//------------------------------------------------------------------------------
	public Trieda(HashMap<Ziak, List<VelkonocneVajce>> trieda) {
		super();
		this.trieda = trieda;
	}

	public HashMap<Ziak, List<VelkonocneVajce>> getTrieda() {
		return trieda;
	}

	@Override
	public String toString() {
		return "Trieda [trieda=" + trieda + "]";
	}
}
