package beans;

import java.util.ArrayList;
import business.EspectaculoMultDTO;
import business.EspectaculoPuntDTO;
import business.EspectaculoTempDTO;

public class AllEspsBean implements java.io.Serializable
{

	private static final long serialVersionUID = -6420015779777493994L;
	
	private ArrayList <EspectaculoPuntDTO> espPunts;
	private ArrayList <EspectaculoMultDTO> espMults;
	private ArrayList <EspectaculoTempDTO> espTemps;
	private int numPunt;
	private int numMult;
	private int numTemp;
	
	public ArrayList <EspectaculoPuntDTO> getAllPunt()
	{
		return espPunts;
	}
	
	public ArrayList <EspectaculoMultDTO> getAllMult()
	{
		return espMults;
	}
	
	public ArrayList <EspectaculoTempDTO> getAllTemp()
	{
		return espTemps;
	}
	public void setAllPunt(ArrayList<EspectaculoPuntDTO> punts)
	{
		this.espPunts = punts;
	}
	
	public void setAllMult(ArrayList<EspectaculoMultDTO> mults)
	{
		this.espMults = mults;
	}
	
	public void setAllTemp(ArrayList<EspectaculoTempDTO> temps)
	{
		this.espTemps = temps;
	}
	
	public int getNumPunt()
	{
		return numPunt;
	}
	
	public int getNumMult()
	{
		return numMult;
	}
	
	public int getNumTemp()
	{
		return numTemp;
	}
	
	public void setNumPunt(int num)
	{
		this.numPunt = num;
	}
	
	public void setNumMult(int num)
	{
		this.numMult = num;
	}
	
	public void setNumTemp(int num)
	{
		this.numTemp = num;
	}
}
