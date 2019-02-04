export class Message {

    constructor(
    	public idMessage?: number, 
    	public joueur?: string, 
    	public contenu?: string, 
    	public dateCreation?: string,
    	public visible?: number, 
    	public chat?: any, 
    	) { }
}