package com.example.demo.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Attachment;
import com.example.demo.domain.entity.Profile;
import com.example.demo.domain.entity.ProfileAndAttachment;
import com.example.demo.domain.repository.AttachmentRepository;
import com.example.demo.domain.repository.ProfileRepository;

@Service
@Transactional
public class ProfileAttachmentService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private AttachmentRepository attachmentRepository;

	public void deleteAll() {

		profileRepository.deleteAll();
		attachmentRepository.deleteAll();
	}

	public void deleteById(Integer id) {
		profileRepository.deleteById(id);
		attachmentRepository.deleteById(id);
	}

	/**
	 * 受け取ったProfileAndAttachmentオブジェクトをもとにprofileテーブル、attachmentテーブルに レコードを登録する。
	 *
	 * @param entity
	 *            ProfileAndAttachment型オブジェクト(jsonを想定)
	 * @return ProfileAndAttachment
	 */
	public ProfileAndAttachment insertOrUpdate(ProfileAndAttachment entity) {

		// Profileテーブルに登録するデータ
		Profile profile = new Profile();
//		profile.setId(entity.getProfile().getId());
//		profile.setName(entity.getProfile().getName());
//		profile.setAge(entity.getProfile().getAge());
//		profile.setCompany(entity.getProfile().getCompany());

		BeanUtils.copyProperties(entity, profile);

		// Attachmentテーブルに登録するデータ
		Attachment attachment = new Attachment();
//		attachment.setId(entity.getAttachment().getId());
//		attachment.setBelonging(entity.getAttachment().getBelonging());
//		attachment.setWork(entity.getAttachment().getWork());
		BeanUtils.copyProperties(entity, attachment);

		// profileテーブルに登録
		profileRepository.save(profile);
		// attachmentテーブルに登録
		attachmentRepository.save(attachment);

		// 返却するjsonデータの作成
		ProfileAndAttachment data = new ProfileAndAttachment();
		data.setProfile(profile);
		data.setAttachment(attachment);
		return data;
	}

	public ProfileAndAttachment getById(Integer id) {

		ProfileAndAttachment data = new ProfileAndAttachment();

		Optional<Profile> profile = profileRepository.findById(id);

		Optional<Attachment> attachment = attachmentRepository.findById(id);

		if (isSameId(profile.get(), attachment.get())) {

			data.setProfile(profile.get());
			data.setAttachment(attachment.get());
		}
		return data;
	}

	public List<ProfileAndAttachment> getAllData() {

		List<ProfileAndAttachment> datas = new ArrayList<>();

		List<Profile> profiles = profileRepository.findAll();

		List<Attachment> attachment = attachmentRepository.findAll();

		for (int i = 0; i < profiles.size(); i++) {
			for (int j = 0; j < attachment.size(); j++) {

				if (isSameId(profiles.get(i), attachment.get(j))) {

					ProfileAndAttachment data = new ProfileAndAttachment();

					data.setProfile(profiles.get(i));
					data.setAttachment(attachment.get(j));
					datas.add(data);

				}
			}
		}
		return datas;

	}

	private boolean isSameId(Profile profile, Attachment attachment) {

		if (profile == null || attachment == null) {
			return false;
		}

		if (profile.getId() == null) {
			return false;
		}

		return profile.getId().equals(attachment.getId());
	}
}
