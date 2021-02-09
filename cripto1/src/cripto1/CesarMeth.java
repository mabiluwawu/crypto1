package cripto1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CesarMeth {
	static char alpha[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',}; 
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader phrase = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Entre la phrase à décoder");
        String str = phrase.readLine();
        
        //comptRepettion(str); 
        char n = chercherE(str);
        int decal = decalage(n);
 
        System.out.println("\nle mot(ou phrase) en claire est : \n"+ decrypt(decal, str));
           
	}
	
    /*static void comptRepettion(String text){
        int count[] = new int[256]; 
  
        int lngr = text.length(); 
  
        // Initialisation l'index du tableau count[]
        for (int i = 0; i < lngr; i++) 
            count[text.charAt(i)]++; 
 
        char ch[] = new char[text.length()]; 
        
        for (int i = 0; i < lngr; i++) {
        	
            ch[i] = text.charAt(i); 
            int occurrence = 0; 
            
            for (int j = 0; j <= i; j++) { 
                // Vérifier si des correspondances ont été trouvées
                if (text.charAt(i) == ch[j])
                    occurrence++;                 
            } 
            if (occurrence == 1)  
                System.out.println("Nombre de repetition de " + text.charAt(i) + " est:" + count[text.charAt(i)]);             
        } 
    }*/	
	static char chercherE(String text1) {
		ArrayList<Character> lettres = new <Character>ArrayList();
		int plusRp = 0;
		char[] cars = text1.toCharArray();
		int[] nombres = new int[cars.length];
		
		// 
		for (int i = 0; i < alpha.length; i++) {
			for (int j = 0; j < cars.length; j++) {
				if (alpha[i] == cars[j]) {
					nombres[i]++;
				}
			}
		}
		
		// la recuperation de nombre de repetition de la lettre avec une fraquence la plus élévé que les autres
		for (int i = 0; i < alpha.length; i++) {
			if (nombres[i] > plusRp) {
				plusRp = nombres[i];
			}
			
		}
		// recuperation de la lettre la plus repetté dans la phrase
		for (int i = 0; i < alpha.length; i++) {
			if (nombres[i] == plusRp && !lettres.contains(cars[i]))
				lettres.add(alpha[i]);
		}
		if (lettres.size() < 2) {
			System.out.println("\nla lettre qui répresente E dans la phrase (mot) crypté est : " + lettres.get(0) + " (elle se repete "+ plusRp + " fois.)");
		}
		
		return lettres.get(0);
	}
	
	//la fonction decrypatage comme son l'indique 
	public static String decrypt(int n, String phrase)  {

        char[] phr = phrase.toCharArray();
        char[] txt = new char[phr.length];   
        int p1, p2;      
        for(int i = 0; i < phr.length; i++) {
           p1 = carctRecp(phr[i], alpha);    
           p2 = newCaract(p1, -n);   
           if(p2 == -1) txt[i] = ' ';    
           else txt[i] = alpha[p2];   
       }   
     // avec l'ancapuslation d'un tableau de char en string, ont returne une chiane de caractaire
       return new String(txt);   
	}
	
	static int decalage(char car) {
		int i,j=0,p=0,r=-4;
		for(i= 4; car != alpha[i];) {
			j++;
			i++;
			for(;i>25;) {
				if(car == alpha[p] )
					return r;
			r++;
			p++;
			}
		}
		
		return j;
	}	
	
    private static int carctRecp(char c, char[] tab)  {
        for(int i = 0; i < tab.length; i++)   { 
            if(tab[i] == c) return i;
        }   return -1; 
     }  
    private static int newCaract(int p,int n)  {
        int pr = p;
     // -1 signifie que le caractere n'a pas été trouvé dans l'alphabet (caractere spécial, chiffre, espace, etc.)
        if(p <= -1) { 
            pr = -1;   
        } else {
            int i = 0;
            while(i < nbr(n)) {
                if(n < 0) {
                    if(pr - 1 == -1) pr = 25;
                    else pr--;
                } else {
                    if(pr + 1 >= 25) pr = 0;
                    else pr++;
                }
                i++;
            }
        }
        return pr;
    }
    public static int nbr(int n)  {
        if(n >= 0) return n;
        else return -n;
    }
}