package com.loupgarou.model;

import java.time.LocalDateTime;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.loupgarou.divers.*;

@Entity
@Table(name = "partie")
@DiscriminatorValue("Partie")



public class Partie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PART_ID")
	private int id = 0;

	//private ArrayList<Villageois> joueurs = new ArrayList<Villageois>();
	
	@Column(name="PART_NB_VILLAGEOIS")
	private int nbVillageois;
	@Column(name="PART_NB_LOUP")
	private int nbLoups;
	@Column(name="PART_ETAT")
	private Boolean etat;
	
	@OneToOne
	@JoinColumn(name="PART_CHAT_ID")
	private Chat chat;
	
	
	@Column(name="PART_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation = new Date();
	
	
	//, cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
	@OneToMany(mappedBy="partie",fetch=FetchType.EAGER)
	private List<Villageois> joueurs;
		
	public Partie()
	{
		nbVillageois = 0;
		nbLoups = 0;
		etat = true;
		//chat = new Chat();
		joueurs = new ArrayList<Villageois>();
	}
	
	

//	public int getNbPartie() {
//	@NamedQueries({
//		@NamedQuery(
//				name = "Partie.nbParties",
//				query = " select count(p) from Partie p"
//				)
//	})
//
//
//	Query myquery = this.em.createNamedQuery("Partie.nbParties", Partie.class);
//
//
//		return (int);
//	}
//
//	public static void setNbPartie(int nbPartie) {
//		Partie.nbPartie = nbPartie;
//	}
//
	
    public List<Villageois> getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(List<Villageois> joueurs) {
        this.joueurs = joueurs;
    }

	public int getNbVillageois() {
		return nbVillageois;
	}

	public void setNbVillageois(int nbVillageois) {
		this.nbVillageois = nbVillageois;
	}

	public int getNbLoups() {
		return nbLoups;
	}

	public void setNbLoups(int nbLoups) {
		this.nbLoups = nbLoups;
	}

	public Boolean getEtat() {
		return etat;
	}

	public void setEtat(Boolean etat) {
		this.etat = etat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	
	
	
	
	
//	
//	public int getVillageoisIndex(String nom)
//	{
//		int i = 0;
//		for(Villageois v : this.joueurs)
//		{
//			if(v.getUserName() == nom)
//			{
//				return i;
//			}
//			i++;
//			
//		}
//		return -1;
//	}
//	
//	public void ajouterJoueur(Utilisateur u)
//	{
//		Villageois nouvJoueur = new Villageois(u.getUserID(), u.getUserName(), u.getPassWord(), "", this);
//		this.joueurs.add(nouvJoueur);
//	}
//	
//	public void initiatePartie()
//	{
//		int choix = -1;
//		while(choix != 0)
//		{
//			System.out.println(" ##### INITIALISATION DE LA PARTIE  #####  ");
//			System.out.println("1- Afficher les participants");
//			System.out.println("2- Démarrer le jeu");
//			
//			choix = fonctions.lireEntier();
//			
//			switch(choix)
//			{
//			case 1 : 
//				for(Villageois v : joueurs)
//				{
//					v.afficherUtilisateur();
//				}
//				break;
//			case 2 : 
//				if(joueurs.size() < 5)
//				{
//					System.out.println("Au moins 5 joueurs sont nécessaires.");
//				}
//				else
//				{
//					return;
//				}
//				break;
//			default :
//				choix = -1;
//				break;
//			}
//		}
//	}
//	
	public void distribuerRole()
	{
		//1- Villageois
		//2- LoupGarou
		//3- Cupidon
		//4- Sorcière
		//5- Voyante
		//6- Chasseur
		//7- Petite fille
		List<Integer> roles = Arrays.asList(1, 1, 2, 2, 3);
		if(this.joueurs.size() >= 6)
		{
			roles.add(4);
		}
		if(this.joueurs.size() >= 7)
		{
			roles.add(5);
		}
		if(this.joueurs.size() >= 8)
		{
			roles.add(2);
		}
		if(this.joueurs.size() >= 9)
		{
			roles.add(6);
		}
		if(this.joueurs.size() >= 10)
		{
			roles.add(7);
		}
		if(this.joueurs.size() >= 11)
		{
			while(roles.size() < this.joueurs.size())
			{
				double tmpdb = Math.random();
				int tmp = (int)Math.round(tmpdb);
				roles.add(tmp);
			}
		}
		
		Collections.shuffle(roles);
		
		//Mise a jour des rôles des joueurs
		ArrayList<Villageois> newJoueurs = new ArrayList<Villageois>();
		
		for(int i = 0; i < this.joueurs.size(); i++)
		{
			switch(roles.get(i))
			{
			//Villageois
			case 1 :
				this.joueurs.get(i).setRole("Villageois");
				newJoueurs.add(this.joueurs.get(i)); 				
				break;
			//Loup
			case 2 :
				Loup nouvLoup = new Loup(this.joueurs.get(i));
				nouvLoup.setRole("Loup");
				newJoueurs.add(nouvLoup);
				break;
			//Cupidon
			case 3 :
				Cupidon nouvCupidon = new Cupidon(this.joueurs.get(i));
				nouvCupidon.setRole("Cupidon");
				newJoueurs.add(nouvCupidon);
				break;
			//Sorcière
			case 4 :
				Sorciere nouvSorciere = new Sorciere(this.joueurs.get(i));
				nouvSorciere.setRole("Sorciere");
				newJoueurs.add(nouvSorciere);
				break;
		
			//Voyante
			case 5 :
				Voyante nouvVoyante = new Voyante(this.joueurs.get(i));
				nouvVoyante.setRole("Voyante");
				newJoueurs.add(nouvVoyante);
				break;
				
			//Chasseur
			case 6 :
				Chasseur nouvChasseur = new Chasseur(this.joueurs.get(i));
				nouvChasseur.setRole("Chasseur");
				newJoueurs.add(nouvChasseur);
				break;
				
			//Petite fille
			case 7 :
				PetiteFille nouvPetiteFille = new PetiteFille(this.joueurs.get(i));
				nouvPetiteFille.setRole("PetiteFille");
				newJoueurs.add(nouvPetiteFille);
				break;
			default :
				this.joueurs.get(i).setRole("Villageois");
				newJoueurs.add(this.joueurs.get(i)); 				
				break;
			}
		}
		
		this.joueurs = newJoueurs;
		
//		for(Villageois j : this.joueurs)
//		{
//			System.out.println(j.getUserName() + " : " + j.getRole());
//		}
		
		
	}
//	
	
	public void endormirVillage() {
		for (Villageois v : this.getJoueurs()) {
			v.setEndormit(true);
			// peut être utilise pour endormir villageois et loups durant leurs phases de jeux 
		}
	}
	
	public void reveillerVillage() {
		for (Villageois v : this.getJoueurs()) {
			v.setEndormit(false);
		}
	}
	
	public void reveillerLoup() {
		for (Villageois v : this.getJoueurs()) {
			if(v.getRole()=="Loup") { v.setEndormit(false); }
		}
	}
	
	
	public Villageois designerCapitaine() {
		//generation d'un nombbre aléatoire compris entre 0 et le nombre de joueurs
		int rd = (int)Math.round(Math.random() * (this.joueurs.size() - 0));
		
		this.joueurs.get(rd-1).setCapitaine(true);
		
		return this.joueurs.get(rd-1);
	}
	
	public Villageois compterVote() {
		List<Villageois> cibles = new ArrayList<Villageois>();
		List<Integer> depouillage = new ArrayList<Integer>();
		
		for (Villageois v : this.getJoueurs()) {
			int added = 1;
			if (v.getCapitaine()==true) {added = 2;}
			
			this.joueurs.get(v.getVote());
			if(cibles.contains(this.joueurs.get(v.getVote()))==false) {
				cibles.add(this.joueurs.get(v.getVote()));
				depouillage.add(added);
			}
			else {
				//cibles.indexOf(this.joueurs.get(v.getVote()));
				depouillage.set(cibles.indexOf(
							this.joueurs.get(v.getVote())), 
							(depouillage.get(cibles.indexOf(this.joueurs.get(v.getVote())))+added));
			}
		}
		Boolean difference = true;
		while (difference==true && cibles.size()>1) {
			if(depouillage.get(0)<depouillage.get(1)) {
				depouillage.remove(0);
				cibles.remove(0);
			}
			else {
				if(depouillage.get(0)>depouillage.get(1)) {
					depouillage.remove(1);
					cibles.remove(1);
				}
				else {
					difference = false;
				}
			}
		}
		Villageois victime = null;
		if(cibles.size()==1) {
			System.out.println("Un villageois a ete lynche =)");
			victime = cibles.get(0);
			System.out.println(victime.getUserName()+" a donc ete tue avec "+depouillage.get(0)+" votes.");
		}
		else {
			System.out.println("Egalites de votes avec : ");
			for (Villageois v : cibles) {
				System.out.println(v.getUserName()+" avec "+depouillage.get(cibles.indexOf(v))+" votes.");
			}
			System.out.println("Vote relance.");
		}
		return victime;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

//	 public void initialiseJoueurs() {
//	        // but : depuis  int this.getId() interroger base et récupérer tt données
//	        IDAOVillageois daoVillageois = new DAOVillageoisSQL();
//	        List<Villageois> villageois = daoVillageois.findAll();
//	        ArrayList<Integer> joueur = new ArrayList<Integer>();
//	        for (Villageois v : villageois) {
//	            if (v.getPartie().getId()== this.getId()) {
//	                joueur.add(v.getVillID()); 
//	            }
//	        }
//	        this.setJoueurs(joueur);
//	    }	
	 
//	    public ArrayList<Villageois> getListJoueurs(List<Integer> liste){
//	        IDAOVillageois daoVillageois = new DAOVillageoisSQL();
//	        ArrayList<Villageois> result = new ArrayList<Villageois>();
//	        for (Integer i : liste) {
//	            result.add(daoVillageois.findById(i));
//	        }
//	        return result;
//	    }
//	    
//	    public Villageois getVillageois(ArrayList<Villageois> village, String name) {
//			for (Villageois v : village) {
//				if(v.getUserName()==name) {
//					return v;
//				}
//			}
//			return null;
//		}
//		
		public void terminerPartie()
		{
			for(Villageois v : this.joueurs)
			{
				v.setRole("Utilisateur");
			}
		}

}
