package com.itwill.ver02;

import com.itwill.ver01.Contact;

public class ContactDaoImpl implements ContactDao {

	// ------ singleton
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

	private Contact[] contacts = new Contact[MAX_LENGTH];

	private int count = 0;  // 현재 몇개가 저장되어 있는지

	/**
	 * 연락처 정보를 저장하는 배열이 가득 차 있으면 true, 빈 공간(null)이 있으면 false를 리턴.
	 * 
	 * @return true/false
	 */
	public boolean isMemoryFull() {
		return (count == MAX_LENGTH);
	}

	/**
	 * 인덱스가 배열의 유효한 인덱스인 지를 검사. 인덱스가 0 이상이고, 배열에 저장된 원소 개수보다 작으면 true, 그렇지 않으면
	 * false를 리턴.
	 * 
	 * @param index 검사할 인덴스(정수)
	 * @return true/false
	 */
	public boolean isValidIndex(int index) {
		return (index >= 0) && (index < count);
	}

	@Override
	public int create(Contact contact) {
		if (isMemoryFull()) {
			return 0;
		}

		contacts[count] = contact;
		count++;

		return 1;

	}

	@Override
	public Contact[] read() {
		Contact[] result = new Contact[count];
		for (int i = 0; i < count; i++) {
			result[i] = contacts[i];
		}
		return result;
	}

	@Override
	public Contact read(int index) {
		if (isValidIndex(index)) {
			return contacts[index];
		} else {
			return null;
		}
	}

	@Override
	public int update(int index, Contact contact) {
		if (isValidIndex(index) && contact != null) {
			contacts[index].setName(contact.getName());
			contacts[index].setPhone(contact.getPhone());
			contacts[index].setEmail(contact.getEmail());
			return 1;
		} else {
			return 0;
		}
	}

}
