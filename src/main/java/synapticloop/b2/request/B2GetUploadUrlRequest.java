package synapticloop.b2.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import synapticloop.b2.exception.B2ApiException;
import synapticloop.b2.response.B2AuthorizeAccountResponse;
import synapticloop.b2.response.B2GetUploadUrlResponse;

/**
 * <p>Gets an URL to use for uploading files.</p>
 * 
 * <p>When you upload a file to B2, you must call b2_get_upload_url first to get the URL for uploading directly to the place where the file will be stored.</p>
 * <p>TODO: Describe how you know when to get a new upload URL.</p>
 * 
 * 
 * This is the interaction class for the <strong>b2_get_upload_url</strong> api calls, this was
 * generated from the backblaze api documentation - which can be found here:
 * <a href="http://www.backblaze.com/b2/docs/b2_get_upload_url.html">http://www.backblaze.com/b2/docs/b2_get_upload_url.html</a>
 * 
 * @author synapticloop
 */

public class B2GetUploadUrlRequest extends BaseB2Request {
	private static final String B2_GET_UPLOAD_URL = "/b2api/v1/b2_get_upload_url";
	private B2AuthorizeAccountResponse b2AuthorizeAccountResponse = null;
	private String bucketId = null;

	public B2GetUploadUrlRequest(B2AuthorizeAccountResponse b2AuthorizeAccountResponse, String bucketId) {
		this.b2AuthorizeAccountResponse = b2AuthorizeAccountResponse;
		this.bucketId = bucketId;
	}
	
	public B2GetUploadUrlResponse getResponse() throws B2ApiException {
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("bucketId", bucketId);

			connection = getApiPostConnection(B2_GET_UPLOAD_URL, b2AuthorizeAccountResponse);
			inputStream = writePostData(connection, map);

			return(new B2GetUploadUrlResponse(inputStream));
		} catch (IOException ex) {
			throw new B2ApiException(ex);
		} finally {
			tidyUp(inputStream, connection);
		}
	}
}