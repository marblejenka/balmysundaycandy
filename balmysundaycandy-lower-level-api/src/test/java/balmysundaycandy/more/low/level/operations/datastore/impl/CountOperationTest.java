package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.Integer64Proto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.Query;

public class CountOperationTest {
	private static final String kind = "�Ă߂��炸���Ƒ҂��Ă��񂾂�I�H�C���f�b�N�X�̋L���������Ȃ��Ă����ށA�C���f�b�N�X�̓G�ɂ܂��Ȃ��Ă����ށE�E�E����ȒN�����΂��āA�N�����]�ލō��ȃn�b�s�[�G���h���Ă���B���܂ő҂��ł���Ă��񂾂�H����ȓW�J���E�E�E���̂��߂ɂ����܂Ŏ���H�����΂��Ă����񂾁I�H�Ă߂��̂��̎�ł�������l�̏��̎q�������Č�������Đ������񂶂�˂��̂���H���O�炾���Ď�l���̕�����������I�H�e���Ȃ񂩂Ŗ������Ă񂶂�˂��A���������Ă�������l�̏��̎q�����Ă��񂶂�Ȃ��̂���I�H��������A����͑S�R�I����Ă˂��A�n�܂��Ă��炢�˂��E�E�E������Ƃ��炢�����v�����[�O�Ő�]���Ă񂶂�˂���I���L�΂��Γ͂��񂾁I���������Ɏn�߂悤���A���p�t�I�I";

	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

	@Test
	public void testCallQuery() {
		Query request = new Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind(kind);

		Integer64Proto response = DatastoreOperations.COUNT.call(request);

		assertThat(response, is(not(nullValue())));
		assertThat(response.getValue(), is(0L));
	}

	@Test
	public void testCallAsyncQueryApiConfig() throws InterruptedException, ExecutionException {
		Query request = new Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind(kind);

		Future<Integer64Proto> response = DatastoreOperations.COUNT.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
		assertThat(response.get().getValue(), is(0L));
	}
}
