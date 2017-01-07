package org.usfirst.frc.team6013.logger;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Set;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class MemoryTable implements ITable {
	private HashMap<String, Object> table;
	
	public MemoryTable() {
		table = new HashMap<String, Object>();
	}
	
	/* keys */
	@Override
	public Set<String> getKeys() {
		return getKeys(0);
	}

	@Override
	public Set<String> getKeys(int arg0) {
		if(arg0 == 0) {
			return table.keySet();
		} else {
			//What is a bitmask of types???
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean containsKey(String key) {
		return table.containsKey(key);
	}
	
	@Override
	public void delete(String key) {
		table.remove(key);
	}
	
	/* get functions, exception version */
	@Override
	public boolean getBoolean(String key) throws TableKeyNotDefinedException {
		return getValueConverted(Boolean.class, key);
	}

	@Override
	public int getInt(String key) throws TableKeyNotDefinedException {
		return getValueConverted(Integer.class, key);
	}
	
	@Override
	public Object getValue(String key) throws TableKeyNotDefinedException {
		return getValueConverted(Object.class, key);
	}
	
	@Override
	public boolean[] getBooleanArray(String key) throws TableKeyNotDefinedException {
		return getValueConverted(boolean[].class, key);
	}
	
	@Override
	public double getDouble(String key) throws TableKeyNotDefinedException {
		return getValueConverted(Double.class, key);
	}
	
	@Override
	public double getNumber(String key) throws TableKeyNotDefinedException {
		return getDouble(key);
	}

	@Override
	public String getString(String key) throws TableKeyNotDefinedException {
		return getValueConverted(String.class, key);
	}

	@Override
	public String[] getStringArray(String key) throws TableKeyNotDefinedException {
		return getValueConverted(String[].class, key);
	}

	@Override
	public double[] getNumberArray(String key) throws TableKeyNotDefinedException {
		return getValueConverted(double[].class, key);
	}
	
	@Override
	public void retrieveValue(String key, Object value) throws TableKeyNotDefinedException {
		value = getValue(key);
	}
	
	/* get functions, checked version */
	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		return getValueConverted(Boolean.class, key, defaultValue);
	}
	
	@Override
	public int getInt(String key, int defaultValue) {
		return getValueConverted(Integer.class, key, defaultValue);
	}
	
	@Override
	public Object getValue(String key, Object defaultValue) {
		return getValueConverted(Object.class, key, defaultValue);
	}
	
	@Override
	public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
		return getValueConverted(boolean[].class, key, defaultValue);
	}
	
	@Override
	public Boolean[] getBooleanArray(String key, Boolean[] defaultValue) {
		return getValueConverted(Boolean[].class, key, defaultValue);
	}
	
	@Override
	public double getDouble(String key, double defaultValue) {
		return getValueConverted(Double.class, key, defaultValue);
	}
	
	@Override
	public double getNumber(String key, double defaultValue) {
		return getDouble(key, defaultValue);
	}
	
	@Override
	public String getString(String key, String defaultValue) {
		return getValueConverted(String.class, key, defaultValue);
	}
	
	@Override
	public String[] getStringArray(String key, String[] defaultValue) {
		return getValueConverted(String[].class, key, defaultValue);
	}
	
	@Override
	public double[] getNumberArray(String key, double[] defaultValue) {
		return getValueConverted(double[].class, key, defaultValue);
	}

	@Override
	public Double[] getNumberArray(String key, Double[] defaultValue) {
		return getValueConverted(Double[].class, key, defaultValue);
	}
	
	/* put version, must use class versions of primitive variables */
	@Override
	public boolean putBoolean(String key, boolean value) {
		return putValueInternal(key, new Boolean(value));
	}
	
	@Override
	public boolean putInt(String key, int value) {
		return putValueInternal(key, new Integer(value));
	}
	
	@Override
	public boolean putValue(String key, Object value) {
		return putValueInternal(key, value);
	}
	
	@Override
	public boolean putBooleanArray(String key, boolean[] value) {
		return putValueInternal(key, value);
	}

	@Override
	public boolean putBooleanArray(String key, Boolean[] value) {
		return putValueInternal(key, value);
	}
	
	@Override
	public boolean putDouble(String key, double value) {
		return putValueInternal(key, new Double(value));
	}
	
	@Override
	public boolean putNumber(String key, double value) {
		return putDouble(key, value);
	}
	
	@Override
	public boolean putString(String key, String value) {
		return putValueInternal(key, value);
	}

	@Override
	public boolean putStringArray(String key, String[] value) {
		return putValueInternal(key, value);
	}
	
	@Override
	public boolean putNumberArray(String key, double[] value) {
		return putValueInternal(key, value);
	}

	@Override
	public boolean putNumberArray(String key, Double[] value) {
		return putValueInternal(key, value);
	}
	
	/* unimplemented */
	@Override
	public void clearFlags(String arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clearPersistent(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsSubTable(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getFlags(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] getRaw(String arg0) throws TableKeyNotDefinedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] getRaw(String arg0, byte[] arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ITable getSubTable(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> getSubTables() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPersistent(String arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean putRaw(String arg0, byte[] arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean putRaw(String arg0, ByteBuffer arg1, int arg2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFlags(String arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPersistent(String arg0) {
		throw new UnsupportedOperationException();
	}

	/*Table Listeners (I believe only used for network handling) */
	@Override
	public void addSubTableListener(ITableListener arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addSubTableListener(ITableListener arg0, boolean arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addTableListener(ITableListener arg0) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void addTableListener(ITableListener arg0, boolean arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addTableListener(String arg0, ITableListener arg1, boolean arg2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addTableListenerEx(ITableListener arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addTableListenerEx(String arg0, ITableListener arg1, int arg2) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void removeTableListener(ITableListener arg0) {
		throw new UnsupportedOperationException();
	}
	
	/* internal functions */
	@SuppressWarnings("unchecked")
	private <T> T getValueConverted(Class<T> type, String key) throws TableKeyNotDefinedException {
		Object value = table.get(key);
		
		if ((value == null) || (type.isInstance(value) == false)) {
			throw new TableKeyNotDefinedException(key);
		} else {
			return (T)value;
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getValueConverted(Class<T> type, String key, T defaultValue) {
		Object value = table.get(key);
		
		if ((value == null) || (type.isInstance(value) == false)) {
			return defaultValue;
		} else {
			return (T)value;
		}
	}
	
	private <T> boolean putValueInternal(String key, T value) {
		table.put(key, value);
		return true;
	}
}
