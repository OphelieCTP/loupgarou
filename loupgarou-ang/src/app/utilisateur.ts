export class Utilisateur {

    constructor(
    	public userID?: number, 
    	public userName?: string, 
    	public passWord?: string, 
    	public nbPlaintes?: number, 
    	public isConnected?: boolean, 
    	public isBanni?: boolean,
    	public email?: string, 
    	public dateNaissance?: string,
    	public role?: string, 
    	public chat?: any) { }
}