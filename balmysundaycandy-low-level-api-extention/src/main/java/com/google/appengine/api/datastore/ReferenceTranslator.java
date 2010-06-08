package com.google.appengine.api.datastore;

import com.google.storage.onestore.v3.OnestoreEntity.Reference;

public class ReferenceTranslator {

	public static Reference key2reference(Key key) {
		return KeyTranslator.convertToPb(key);
	}

	public static Reference kind2reference(String kind) {
		return KeyTranslator.convertToPb(kindname2key(kind));
	}

	public static Reference path2reference(Key parent, String kind) {
		return KeyTranslator.convertToPb(path2key(parent, kind));
	}

	public static Key reference2key(Reference reference) {
		return KeyTranslator.createFromPb(reference);
	}

	public static Key kindname2key(String kind) {
		return new Key(kind);
	}

	public static Key path2key(Key parent, String kind) {
		return new Key(kind, parent);
	}
}
