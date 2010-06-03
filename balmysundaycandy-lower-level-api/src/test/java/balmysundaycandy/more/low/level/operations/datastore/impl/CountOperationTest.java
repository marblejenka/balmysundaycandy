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
	private static final String kind = "てめぇらずっと待ってたんだろ！？インデックスの記憶を消さなくてもすむ、インデックスの敵にまわらなくてもすむ・・・そんな誰もが笑って、誰もが望む最高なハッピーエンドってやつを。今まで待ち焦がれてたんだろ？こんな展開を・・・何のためにここまで歯を食いしばってきたんだ！？てめぇのその手でたった一人の女の子を助けて見せるって誓ったんじゃねえのかよ？お前らだって主人公の方がいいだろ！？脇役なんかで満足してんじゃねえ、命を懸けてたった一人の女の子を守りてぇんじゃないのかよ！？だったら、それは全然終わってねぇ、始まってすらいねぇ・・・ちょっとくらい長いプロローグで絶望してんじゃねぇよ！手を伸ばせば届くんだ！いい加減に始めようぜ、魔術師！！";

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
