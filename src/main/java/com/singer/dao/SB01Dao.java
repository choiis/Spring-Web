package com.singer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.singer.vo.SB01Vo;

@Repository("sb01Dao")
public class SB01Dao extends SuperDao {

	private static final String namespace = "com.singer.mappers.SB01";

	public int insertSB01Vo(SB01Vo sb01Vo) throws Exception {
		return insert(namespace + ".insert", sb01Vo);
	}

	@SuppressWarnings("unchecked")
	public List<SB01Vo> selectSB01Vo(SB01Vo sb01Vo) throws Exception {
		return (List<SB01Vo>) selectList(namespace + ".select", sb01Vo);
	}

	@SuppressWarnings("unchecked")
	public List<SB01Vo> selectFindSB01Vo(SB01Vo sb01Vo) throws Exception {
		return (List<SB01Vo>) selectList(namespace + ".selectFind", sb01Vo);
	}

	public SB01Vo selectOneSB01Vo(SB01Vo sb01Vo) throws Exception {
		return (SB01Vo) selectOne(namespace + ".selectOne", sb01Vo);
	}

	public SB01Vo checkUserSB01Vo(SB01Vo sb01Vo) throws Exception {
		return (SB01Vo) selectOne(namespace + ".checkUser", sb01Vo);
	}

	public int updateSB01Vo(SB01Vo sb01Vo) throws Exception {
		return update(namespace + ".update", sb01Vo);
	}

	public int likeSB01Vo(SB01Vo sb01Vo) throws Exception {
		return insert(namespace + ".like", sb01Vo);
	}

	public int hateSB01Vo(SB01Vo sb01Vo) throws Exception {
		return insert(namespace + ".hate", sb01Vo);
	}

	public int clickSB01Vo(SB01Vo sb01Vo) throws Exception {
		return update(namespace + ".click", sb01Vo);
	}

	public int deleteSB01Vo(SB01Vo sb01Vo) throws Exception {
		return delete(namespace + ".delete", sb01Vo);
	}

	public int insertVideo(SB01Vo sb01Vo) throws Exception {
		return insert(namespace + ".insertVideo", sb01Vo);
	}

	public int updateVideo(SB01Vo sb01Vo) throws Exception {
		return update(namespace + ".updateVideo", sb01Vo);
	}

	public String selectVideo(SB01Vo sb01Vo) throws Exception {
		return (String) selectOne(namespace + ".selectVideo", sb01Vo);
	}

	public int likelogSB01Vo(SB01Vo sb01Vo) throws Exception {
		return update(namespace + ".likelog", sb01Vo);
	}

	public int hatelogSB01Vo(SB01Vo sb01Vo) throws Exception {
		return update(namespace + ".hatelog", sb01Vo);
	}
}