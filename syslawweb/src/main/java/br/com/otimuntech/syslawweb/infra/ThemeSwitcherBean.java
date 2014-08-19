package br.com.otimuntech.syslawweb.infra;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ThemeSwitcherBean {
	
	private Map<String, String> themes;  
      
    private String theme;
    
    private Preference preference = new Preference();
      
    public Map<String, String> getThemes() {  
        return themes;  
    }  
  
    public String getTheme() {  
        return theme;  
    }  
  
    public void setTheme(String theme) {  
        this.theme = theme;  
    }  
  
    @PostConstruct  
    public void init() {  
          
        themes = new TreeMap<String, String>();  
        themes.put("Aristo", "aristo");  
        themes.put("Bluesky", "bluesky");  
        themes.put("Casablanca", "casablanca");  
        themes.put("Redmond", "redmond");
    }  
      
    public void saveTheme(){
    	preference.setTheme(preference.getTheme());
    }

	public Preference getPreference() {
		return preference;
	}
    
    
    
}
