package com.rays.ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping("Auth")
public class UserCtl<T extends UserDTO, D extends UserServiceInt> {

	@Autowired
	D userService;

	@Autowired
	AttachmentServiceInt attt;

	@PostMapping("SignUp")
	public ORSResponse add(@RequestBody UserForm form, HttpServletRequest req) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = new UserDTO();

		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassowrd(form.getPassword());

		userService.add(dto);

		res.addData(dto);
		res.addMessage("User Successfully added...!!!");
		return res;
		// run Successfully
	}

	@PostMapping("update/{id}")
	public ORSResponse update(@PathVariable Long id, @RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = userService.findById(id);

		if (dto != null) {

			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setLoginId(form.getLoginId());
			dto.setPassowrd(form.getPassword());
			userService.update(dto);
			res.addMessage("User Data Successfully Updated...!!!");

		} else {
			res.addMessage("User Record not found...!!!");
		}
		return res;
		// run Successfully

	}

	@PostMapping("login/{loginId}")
	public ORSResponse getByloginId(@PathVariable String loginId, UserForm form) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = userService.findByLoginId(loginId);

		if (dto == null) {
			res.addMessage("User Record not found...!!!");
		} else {
			res.addData(dto);
			res.addMessage("User Data Successfully fetch...!!!");
		}
		return res;
		// run Successfully

	}

	@PostMapping("delete/{id}")
	public ORSResponse delete(@PathVariable Long id) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = userService.findById(id);

		if (dto != null) {
			userService.delete(id);
			res.addMessage("User Data Successfully deleted...!!!");
		} else {
			res.addMessage("User Record not found...!!!");
		}
		return res;
		// run Successfully
	}

	@GetMapping("get/{id}")
	public ORSResponse getById(@PathVariable Long id) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = userService.findById(id);

		if (dto != null) {
			res.addData(dto);
			res.addMessage("User Data Successfully Fetch...!!!");
		} else {
			res.addMessage("User Record not found...!!!");
		}
		return res;
	}

	@PostMapping("login")
	public ORSResponse login(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = userService.authenticate(form.getLoginId(), form.getPassword());

		if (dto == null) {
			res.addMessage("loginId & Password Invalid...!!!");
		} else {
			res.addData(dto);
			res.addMessage("User Successfully login...!!!");
		}
		return res;
	}

	@PostMapping("search")
	public ORSResponse search(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = new UserDTO();

		dto.setFirstName(form.getFirstName());
		dto.setDob(form.getDob());

		List<UserDTO> list = userService.search(dto);

		res.addData(list);
		res.addMessage("User Data Successfully fetch...!!!");
		return res;
	}

	@PostMapping("profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		UserDTO userDTO = userService.findById(userId);

		AttachmentDTO doc = new AttachmentDTO(file);

		doc.setDecription("Profile Picture");
		doc.setPath(req.getServletPath());
		doc.setUserId(userId);

		if (userDTO.getImageId() != null && userDTO.getImageId() > 0) {
			doc.setId(userDTO.getImageId());
		}

		Long imageId = attt.save(doc);

		if (userDTO.getImageId() == null || userDTO.getImageId() == 0) {
			userDTO.setImageId(imageId);
			userService.update(userDTO);
		}

		ORSResponse res = new ORSResponse();

		res.addResult("imageId", imageId);
		return res;

	}

	@GetMapping("profilePic/{userId}")
	public @ResponseBody void downloadPic(@PathVariable Long userId, HttpServletResponse res) {

		UserDTO userDTO = userService.findById(userId);

		AttachmentDTO doc = attt.findById(userDTO.getImageId());

		if (doc != null) {
			res.setContentType(doc.getType());
			try {
				OutputStream out = res.getOutputStream();
				out.write(doc.getDoc());
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				res.getWriter().write("Error: File not found...!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@GetMapping("logout")
	public ORSResponse logout(HttpServletRequest request, HttpServletResponse response) {

		ORSResponse res = new ORSResponse();

		HttpSession session = request.getSession();
		session.invalidate();

		res.addMessage("Logout Successfully...!!!");
		return res;

	}

}
