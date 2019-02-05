export class Message {
	public idMessage;
	public chat;

    constructor(
    	public joueur?: string, 
    	public contenu?: string, 
    	public dateCreation?: any,
    	public visible?: string
    	) { }
}