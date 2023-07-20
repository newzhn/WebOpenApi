import {PageContainer, ProForm, ProFormInstance, ProFormText} from '@ant-design/pro-components';
import React, {useEffect, useRef, useState} from 'react';
import {applyUserApiTokenUsingGET, getCurrentUserUsingGET} from "@/services/WebOpenApi-backend/userController";
import {Button, Card, Col, Divider, message, Modal, Row, Typography} from 'antd';
import {CommentOutlined, FieldTimeOutlined, UnlockOutlined, UserOutlined, VerifiedOutlined, LockOutlined} from "@ant-design/icons";
import {loginUsingPOST} from "@/services/WebOpenApi-backend/loginController";
import moment from "moment";

const { Paragraph } = Typography;
const avatarStyle: React.CSSProperties = {
  width: '100%',
  textAlign: 'center',
};
const buttonStyle: React.CSSProperties = {
  marginLeft: '30px',
};

const Profile: React.FC = () => {
  const [data, setData] = useState<API.UserVo>();
  const [visible, setVisible] = useState<boolean>(false);
  const [flag, setFlag] = useState<boolean>(false);
  const [open, setOpen] = useState<boolean>(false);
  const [loading, setLoading] = useState(false);
  const formRef = useRef<
    ProFormInstance<{
      userPassword: string;
    }>
    >();

  /**
   * 加载数据
   */
  const loadData = async () => {
    setLoading(true);
    try {
      const res = await getCurrentUserUsingGET();
      console.log(res.data.user)
      setData(res.data.user);
    }catch (error: any) {
      return {};
    }
    setLoading(false);
  }

  // 显示秘钥
  const showSecretKey = async () => {
    let userPassword = formRef?.current?.getFieldValue('userPassword');

    // 登录
    const res = await loginUsingPOST({
      userAccount: data?.userAccount + '',
      userPassword: userPassword,
    });
    if (res.code === 200) {
      setOpen(false);
      setVisible(true);
      formRef?.current?.resetFields();
      // 存储 token 到 LocalStorage
      localStorage.setItem('Access-Token', res.token);
    }
  };

  // 重置秘钥
  const resetSecretKey = async () => {
    try {
      let userPassword = formRef?.current?.getFieldValue('userPassword');
      // 登录
      const res = await loginUsingPOST({
        userAccount: data?.userAccount + '',
        userPassword: userPassword,
      });
      if (res.code === 200) {
        // 存储 token 到 LocalStorage
        localStorage.setItem('Access-Token', res.token);
        //发送请求申请密钥
        const result = await applyUserApiTokenUsingGET();
        if (result.code === 200) {
          loadData()
          message.success('重置成功！');
          setOpen(false);
          setVisible(true);
        }
      }
    } catch (e: any) {
      console.log(e);
    }
  };

  useEffect(() => {
    loadData();
  },[])

  return (
    <PageContainer title={"个人中心"} loading={loading}>
      <Row gutter={24}>
        <Col span={8}>
          <Card title="个人信息" bordered={false}>
            <Row>
              <Col style={avatarStyle}>
                <img
                  src={data?.userAvatar}
                  alt="avatar"
                  style={{ width: '100px', borderRadius: '50%' }}
                />
              </Col>
            </Row>
            <Divider />
            <Row>
              <Col>
                <UserOutlined /> 用户名称：{data?.userName}
              </Col>
            </Row>
            <Divider />
            <Row>
              <Col>
                <CommentOutlined /> 用户账号：{data?.userAccount}
              </Col>
            </Row>
            <Divider />
            <Row>
              <Col>
                <VerifiedOutlined /> 用户角色：{data?.userRole}
              </Col>
            </Row>
            <Divider />
            <Row>
              <Col>
                <FieldTimeOutlined /> 注册时间：{moment(data?.createTime).format('YYYY-MM-DD HH:mm')}
              </Col>
            </Row>
          </Card>
        </Col>
        <Col span={16}>
          <Card title="秘钥操作" bordered={false}>
            <Row>
              <Col>
                {visible ? (
                  <Paragraph
                    copyable={{
                      text: data?.accessKey,
                    }}
                  >
                    <LockOutlined /> accessKey：{data?.accessKey}
                  </Paragraph>
                ) : (
                  <Paragraph>
                    <UnlockOutlined /> secretKey：*********
                  </Paragraph>
                )}
              </Col>
            </Row>
            <Divider />
            <Row>
              <Col>
                {visible ? (
                  <Paragraph
                    copyable={{
                      text: data?.secretKey,
                    }}
                  >
                    <UnlockOutlined /> secretKey：{data?.secretKey}
                  </Paragraph>
                ) : (
                  <Paragraph>
                    <UnlockOutlined /> secretKey：*********
                  </Paragraph>
                )}
              </Col>
            </Row>
            <Divider />
            <Row>
              <Col>
                {!data?.accessKey ? (
                  <></>
                  ) : (
                    !visible ? (
                      <Button
                        type="primary"
                        onClick={() => {
                          setOpen(true);
                          setFlag(true);
                        }}
                      >
                        查看秘钥
                      </Button>
                    ) : (
                      <Button type="primary" onClick={() => setVisible(false)}>
                        隐藏秘钥
                      </Button>
                    )
                  )
                }
                <Button
                  style={buttonStyle}
                  onClick={() => {
                    setOpen(true);
                    setFlag(false);
                  }}
                  type="primary"
                  danger
                >
                  {data?.accessKey ? '重置秘钥' : '申请密钥'}
                </Button>
              </Col>
            </Row>
          </Card>
        </Col>
      </Row>
      <Modal
        title="查看秘钥"
        open={open}
        onOk={flag ? showSecretKey : resetSecretKey}
        onCancel={() => setOpen(false)}
      >
        <ProForm<{
          userPassword: string;
        }>
          formRef={formRef}
          formKey="check-user-password-form"
          autoFocusFirstInput
          submitter={{
            resetButtonProps: {
              style: {
                display: 'none',
              },
            },
            submitButtonProps: {
              style: {
                display: 'none',
              },
            },
          }}
        >
          <ProFormText.Password name="userPassword" placeholder="请输入用户密码" />
        </ProForm>
      </Modal>
    </PageContainer>
  );
};

export default Profile;
