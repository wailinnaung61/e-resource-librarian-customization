package beans;

public enum BiblioSyncTypes {
	SyncWithItems(1, "Biblio with items "),
	SyncWithoutItems(2, "Biblio without items"),
	SyncWithItemNumber(3, "Sync with item number");
	int typeId;
	String desc;
	BiblioSyncTypes(int typeId,String desc){
		this.typeId = typeId;
		this.desc = desc;
	}
	public int getTypeId() {
		return this.typeId;
	}
	public String getDesc() {
		return this.desc;
	}
}
