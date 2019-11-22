package cn.service.impl;

import java.util.List;

import cn.dao.StudentDao;
import cn.dao.impl.StudentDaoimp;
import cn.entity.Student;
import cn.entity.deal;
import cn.service.StudentService;

public class StudentServiceimp implements StudentService{
	private StudentDao studentDao = null;
	
	public StudentServiceimp(){
		studentDao = new StudentDaoimp();
	}

	@Override
	public List<deal> getStudent(String name) {
		// TODO Auto-generated method stub
		return studentDao.getStudent(name);
	}
	@Override
	public List<deal> getPaihang() {
		// TODO Auto-generated method stub
		return studentDao.getPaihang();
	}
	@Override
	//��ȡ����ѧ����Ϣ
	public List<Student> getAllStudent() {
		return studentDao.getAllStudent();
	}

	@Override
	//��ȡָ��ѧ����Ϣ
	public deal getDealMoreInfo(deal deal) {
		return studentDao.getDealMoreInfo(deal);
	}

	@Override
	//���ѧ��
	public int addStudent(Student student) {
		return studentDao.addStudent(student);
	}

	@Override
	//ɾ��ѧ��
	public int delStudent(deal deal) {
		return studentDao.delStudent(deal);
	}

	@Override
	//�޸�ѧ����Ϣ
	public int modifyStudent(Student student) {
		return studentDao.modifyStudent(student);
	}
	
	//������û��ָ����ѧ��
	public boolean findStudent(Student student){
		return studentDao.findStudent(student);
				
	}

}