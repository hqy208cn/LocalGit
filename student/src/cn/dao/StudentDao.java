package cn.dao;

import java.util.List;

import cn.entity.Student;
import cn.entity.deal;

public interface StudentDao {
	//��ȡ����ѧ����Ϣ
	public List<Student> getAllStudent();
	public List<deal> getStudent(String name);
	public List<deal> getPaihang();
	//��ȡָ��ѧ����Ϣ
	public deal getDealMoreInfo(deal deal);
	//���ѧ��
	public int addStudent(Student student);
	//ɾ��ѧ��
	public int delStudent(deal deal);
	//�޸�ѧ����Ϣ
	public int modifyStudent(Student student);
	//����ָ����ѧ�����ڲ�����
	public boolean findStudent(Student student);

}
