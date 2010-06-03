package balmysundaycandy.extention.datastore.utils;

import java.util.List;

import balmysundaycandy.core.anntations.NotWorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.WorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.WorkOnLocalEnvironment;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.StringProto;
import com.google.apphosting.api.DatastorePb.CompositeIndices;
import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.apphosting.api.DatastorePb.GetResponse;
import com.google.apphosting.api.DatastorePb.GetSchemaRequest;
import com.google.apphosting.api.DatastorePb.Schema;
import com.google.storage.onestore.v3.OnestoreEntity.CompositeIndex;
import com.google.storage.onestore.v3.OnestoreEntity.Path;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

/**
 * datastore soft schema infomation.
 * this class returns protocol buffer.
 * 
 * @author marblejenka
 */
public class DatastoreSoftSchemeInfomation {

	/**
	 * application id
	 */
	private static final String appid = ApiProxy.getCurrentEnvironment().getAppId();

	/**
	 * "sample for accessing datastore by protocol buffer"
	 * 
	 * get "get response" as protocol buffer expression from datastore.
	 * 
	 * @param kind
	 *            kind name
	 * @param id
	 *            id
	 * @return GetResponse object
	 */
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public GetResponse getByProtocolBuffer(String kind, Long id) {
		GetRequest getRequest = new GetRequest();
		{
			Element element = new Element();
			element.setId(id);
			element.setType(kind);

			Path path = new Path();
			path.addElement(element);

			Reference reference = new Reference();
			reference.setApp(appid);
			reference.setPath(path);

			getRequest.addKey(reference);
		}
		GetResponse getResponse = DatastoreOperations.GET.call(getRequest);
		return getResponse;
	}

	/**
	 * get soft scheme information from datastore by protocol buffer.
	 * 
	 * @return scheme protocol buffer
	 */
	@NotWorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public Schema getSchemeByProtocolBuffer() {
		GetSchemaRequest getSchemaRequest = new GetSchemaRequest();
		getSchemaRequest.setApp(appid);
		Schema schema = DatastoreOperations.GET_SCHEMA.call(getSchemaRequest);
		return schema;
	}

	/**
	 * get composite indices information from datastore by protocol buffer.
	 * 
	 * @return composite indices
	 */
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public CompositeIndices getIndicesByProtocolBuffer() {
		StringProto request = new StringProto();
		request.setValue(appid);
		CompositeIndices response = DatastoreOperations.GET_INDICES.call(request);
		return response;
	}

	/**
	 * get composite index list from datastore by protocol buffer.
	 * 
	 * @return composite indices
	 */
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public List<CompositeIndex> getCompositIndexByProtocolBuffer() {
		CompositeIndices response = getIndicesByProtocolBuffer();
		return response.mutableIndexs();
	}
}
