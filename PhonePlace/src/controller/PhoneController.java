package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.GalaxyNote9;
import model.NotePen;
import model.Phone;
import model.SmartPhone;
import model.V40;

public class PhoneController {
	private Map<String, Phone> phones;
	{
		phones = new HashMap<String, Phone>();
		phones.put("GalaxyNote9", new GalaxyNote9());
		phones.put("V40", new V40());
	}
	
	public List<String> method(String...phoneNames) {
		List<String> phoneInfo = new ArrayList<String>();
		for (String phoneName : phoneNames) {
			if (phones.containsKey(phoneName)) {
				if (phones.get(phoneName) instanceof SmartPhone smartPhone && phones.get(phoneName) instanceof NotePen notePen) {
					phoneInfo.add(smartPhone.printInformation(notePen.bluetoothPen()));
				}
			}
		}
		return phoneInfo;
	}
}
