package com.itwill.ver03;

import java.util.ArrayList;
import java.util.List;

import com.itwill.ver01.Contact;

public class ContactDaoImpl implements ContactDao {
	
	// singleton
	private static ContactDaoImpl instance = null;
	
	private ContactDaoImpl() {
		
	}
	
	public static ContactDaoImpl getInstance() {
		if (instance == null) {
			instance = new ContactDaoImpl();
		}
		
		return instance;
	}
	//----- singleton

	private List<Contact> contacts = new ArrayList<>();
	
	
	/**
	 * 인덱스가 배열의 유효한 인덱스인 지를 검사. 인덱스가 0 이상이고, 배열에 저장된 원소 개수보다 작으면 true, 그렇지 않으면
	 * false를 리턴.
	 * 
	 * @param index 검사할 인덴스(정수)
	 * @return true/false
	 */
	public boolean isValidIndex(int index) {
		return (index >= 0) && (index < contacts.size());
	}
	
	@Override
	public int create(Contact contact) {
		contacts.add(contact);
        
        return 1;		
	}

	@Override
	public List<Contact> read() {
		return contacts;
	}

	@Override
	public Contact read(int index) {
		if (isValidIndex(index)) {
			return contacts.get(index);
		} else {
		return null;
		}
	}

	@Override
	public int update(int index, Contact contact) {
		if (isValidIndex(index)) {
			contacts.set(index, contact);
//			contacts.get(index).setName(contact.getName());
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int delete(int index) {
		if (isValidIndex(index)) {
			contacts.remove(index);
			return 1;
		} else {
			return 0;
		}
	}

}
