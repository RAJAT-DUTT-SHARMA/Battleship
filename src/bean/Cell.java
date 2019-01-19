package bean;

import properties.STATUS_CODE;

public class Cell {

	int x;
	int y;
	STATUS_CODE status;
	int veselNo;
	
	public int getVeselNo() {
		return veselNo;
	}
	public void setVeselNo(int veselNo) {
		this.veselNo = veselNo;
	}
	public STATUS_CODE getStatus() {
		return status;
	}
	public void setStatus(STATUS_CODE status) {
		this.status = status;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


	

}
