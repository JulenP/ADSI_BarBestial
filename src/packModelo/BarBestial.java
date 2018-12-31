package packModelo;

public class BarBestial {
	
	private  static BarBestial miBarBestial;
	
	private BarBestial(){	
	}

	public static BarBestial getBarBestial(){
		if (miBarBestial==null){
			miBarBestial = new BarBestial();
		}
		return miBarBestial;
	}
}
