package com.singer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.singer.common.CommonUtil;
import com.singer.common.Constants;
import com.singer.common.DateUtil;
import com.singer.dao.SB01Dao;
import com.singer.dao.SB02Dao;
import com.singer.vo.SB01Vo;
import com.singer.vo.SB02Vo;

@Service("sb01Service")
public class SB01ServiceImpl implements SB01Service {
	@Resource(name = "sb01Dao")
	private SB01Dao sb01Dao;

	@Resource(name = "sb02Dao")
	private SB02Dao sb02Dao;

	@Resource(name = "properties")
	private Properties properties;

	@Transactional
	@Override
	public int insertSB01Vo(SB01Vo sb01Vo, MultipartHttpServletRequest request) throws Exception {

		MultipartFile video = null;
		Iterator<String> itr = request.getFileNames();

		while (itr.hasNext()) {
			video = request.getFile(itr.next());
		}
		sb01Vo.setVideo(video.getOriginalFilename());
		sb01Vo.setRegdate(DateUtil.getTodayTime());
		String path = properties.getProperty("global.save.path");
		File dir = new File(path);
		dir.mkdir();
		FileOutputStream fos = new FileOutputStream(path + "/" + video.getOriginalFilename());
		fos.write(video.getBytes());
		fos.close();

		return sb01Dao.insertSB01Vo(sb01Vo);

	}

	@Override
	public List<SB01Vo> selectSB01Vo(SB01Vo sb01Vo) throws Exception {

		return sb01Dao.selectSB01Vo(sb01Vo);
	}

	@Override
	public List<SB01Vo> selectFindSB01Vo(SB01Vo sb01Vo) throws Exception {

		if (sb01Vo.getSelection() == 1) { // 제목으로 검색
			sb01Vo.setTitle(sb01Vo.getFindText());
			sb01Vo.setUserid(null);
		} else { // 아이디로 검색
			sb01Vo.setUserid(sb01Vo.getFindText());
			sb01Vo.setTitle(null);
		}

		return sb01Dao.selectSB01Vo(sb01Vo);
	}

	@Transactional
	@Override
	public SB01Vo selectOneSB01Vo(SB01Vo sb01Vo, String userid) throws Exception {

		sb01Dao.clickSB01Vo(sb01Vo);
		sb01Vo.setSessionid(userid);
		SB01Vo sb01vo = sb01Dao.selectOneSB01Vo(sb01Vo);
		if (sb01vo.getUserid().equals(userid)) {
			sb01vo.setDeleteYn(true);
		}
		sb01Vo.setShowDate(DateUtil.getDateFormat(sb01Vo.getRegdate()));

		return sb01vo;
	}

	@Override
	public int updateSB01Vo(SB01Vo sb01Vo) throws Exception {
		return sb01Dao.updateSB01Vo(sb01Vo);
	}

	@Transactional
	@Override
	public SB01Vo likeSB01Vo(SB01Vo sb01Vo, String sessionid) throws Exception {
		int like = sb01Vo.getGood() + 1;
		sb01Dao.likeSB01Vo(sb01Vo);

		sb01Vo.setSessionid(sessionid);
		sb01Vo.setDatelog(DateUtil.getTodayTime());

		sb01Dao.likelogSB01Vo(sb01Vo);

		sb01Vo.setResult(Constants.SUCCESS_CODE);
		sb01Vo.setLike(like);
		return sb01Vo;
	}

	@Transactional
	@Override
	public SB01Vo hateSB01Vo(SB01Vo sb01Vo, String sessionid) throws Exception {
		int like = sb01Vo.getGood() - 1;
		sb01Dao.hateSB01Vo(sb01Vo);

		sb01Vo.setSessionid(sessionid);
		sb01Vo.setDatelog(DateUtil.getTodayTime());

		sb01Dao.hatelogSB01Vo(sb01Vo);

		sb01Vo.setResult(Constants.SUCCESS_CODE);
		sb01Vo.setLike(like);
		return sb01Vo;
	}

	@Transactional
	@Override
	public int deleteSB01Vo(SB01Vo sb01Vo) throws Exception {

		SB02Vo sb02Vo = new SB02Vo();
		sb02Vo.setSeq01(sb01Vo.getSeq());

		sb02Dao.delete_seqSB02Vo(sb02Vo);
		String path = properties.getProperty("global.save.path");
		File file = new File(path + "/" + sb01Vo.getVideo());
		file.delete();

		return sb01Dao.deleteSB01Vo(sb01Vo);
	}

	@Override
	public File selectVideo(SB01Vo sb01Vo, HttpServletRequest request) throws Exception {
		sb01Vo = sb01Dao.selectVideo(sb01Vo);
		File file;
		if (CommonUtil.isNull(sb01Vo.getVideo())) {
			String video_path = request.getSession().getServletContext().getRealPath("/resources/video/hyeri.mp4");
			file = new File(video_path);
		} else {
			String path = properties.getProperty("global.save.path");
			file = new File(path + "/" + sb01Vo.getVideo());
		}
		return file;
	}

}
