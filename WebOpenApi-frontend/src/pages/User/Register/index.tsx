import Footer from '@/components/Footer';

import {
  LockOutlined,
  UserOutlined,
  MessageOutlined,
  DribbbleOutlined
} from '@ant-design/icons';
import {LoginForm, ProFormCaptcha, ProFormText} from '@ant-design/pro-components';
import { useEmotionCss } from '@ant-design/use-emotion-css';
import { Helmet,history } from '@umijs/max';
import {Button, message, Tabs} from 'antd';
import React,{ useState } from 'react';
import Settings from '../../../../config/defaultSettings';
import {registerUsingPOST, sendCodeUsingGET} from "@/services/WebOpenApi-backend/registerController";
import {Link} from "@@/exports";

const Register: React.FC = () => {
  const [type, setType] = useState<string>('email');

  const containerClassName = useEmotionCss(() => {
    return {
      display: 'flex',
      flexDirection: 'column',
      height: '100vh',
      overflow: 'auto',
      backgroundImage:
        "url('https://mdn.alipayobjects.com/yuyan_qk0oxh/afts/img/V-_oS6r-i7wAAAAAAAAAAAAAFl94AQBr')",
      backgroundSize: '100% 100%',
    };
  });

  const handleSubmit = async (values: API.RegisterRequest) => {
    try {

      const res = await registerUsingPOST(values);

      if (res.code === 200) {
        const defaultLoginSuccessMessage = '注册成功！';
        message.success(defaultLoginSuccessMessage);
        const urlParams = new URL(window.location.href).searchParams;
        history.push(urlParams.get('redirect') || '/user/login');
        return;
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <div className={containerClassName}>
      <Helmet>
        {'注册'}- {Settings.title}
      </Helmet>
      <div
        style={{
          flex: '1',
          padding: '32px 0',
        }}
      >
        <LoginForm
          contentStyle={{
            minWidth: 280,
            maxWidth: '75vw',
          }}
          logo={<img alt="logo" src="/logo.svg" />}
          title="WebOpenApi"
          subTitle={'免费的开放式API服务平台'}
          submitter={{
            searchConfig: {
              submitText: '注册',
            },
          }}
          initialValues={{
            autoLogin: true,
          }}
          onFinish={async (values) => {
            await handleSubmit(values as API.RegisterRequest);
          }}
        >
          <Tabs
            activeKey={type}
            onChange={setType}
            centered
            items={[
              {
                key: 'email',
                label: '邮箱注册',
              },
            ]}
          />

          {type === 'email' && (
            <>
              {/*账户名*/}
              <ProFormText
                name="userAccount"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined />,
                }}
                placeholder={'请输入账户名'}
                rules={[
                  {
                    required: true,
                    message: '账户名是必填项！',
                  },
                  {
                    min: 4,
                    type: 'string',
                    message: '账号不能小于4位！',
                  },
                ]}
              />
              {/*密码*/}
              <ProFormText.Password
                name="userPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined />,
                }}
                placeholder={'请输入密码'}
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                  {
                    min: 6,
                    type: 'string',
                    message: '密码不能小于6位！',
                  },
                ]}
              />
              {/*QQ*/}
              <ProFormText
                fieldProps={{
                  size: 'large',
                  prefix: <MessageOutlined />,
                }}
                name="qq"
                placeholder={'请输入QQ号用于获取昵称和头像！'}
                rules={[
                  {
                    required: true,
                    message: 'QQ号是必填项！',
                  },
                  {
                    pattern: /^[1-9][0-9]{4,10}$/,
                    message: '不合法的QQ号！',
                  },
                ]}
              />
              {/*邮箱*/}
              <ProFormText
                fieldProps={{
                  size: 'large',
                  prefix: <DribbbleOutlined />,
                }}
                name="email"
                placeholder={'请输入邮箱！'}
                rules={[
                  {
                    required: true,
                    message: '邮箱是必填项！',
                  },
                  {
                    pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                    message: '不合法的邮箱！',
                  },
                ]}
              />
              {/*验证码*/}
              {/*<ProFormCaptcha*/}
              {/*  fieldProps={{*/}
              {/*    size: 'large',*/}
              {/*    prefix: <LockOutlined />,*/}
              {/*  }}*/}
              {/*  captchaProps={{*/}
              {/*    size: 'large',*/}
              {/*  }}*/}
              {/*  placeholder={'请输入验证码！'}*/}
              {/*  captchaTextRender={(timing, count) => {*/}
              {/*    if (timing) {*/}
              {/*      return `${count} ${'秒后重新获取'}`;*/}
              {/*    }*/}
              {/*    return '获取验证码';*/}
              {/*  }}*/}
              {/*  phoneName="email"*/}
              {/*  name="verificationCode"*/}
              {/*  rules={[*/}
              {/*    {*/}
              {/*      required: true,*/}
              {/*      message: '验证码是必填项！',*/}
              {/*    },*/}
              {/*  ]}*/}
              {/*  onGetCaptcha={async (email) => {*/}
              {/*    // 发送请求获取验证码*/}
              {/*    const result = await sendCodeUsingGET({*/}
              {/*      email,*/}
              {/*    });*/}
              {/*    if (result.code === 200) {*/}
              {/*      message.success('验证码发送成功，请注意邮箱查收！');*/}
              {/*    }*/}
              {/*  }}*/}
              {/*/>*/}
            </>
          )}
          <div style={{ textAlign: 'center',marginBottom:'20px'}}>
            <Link to="/user/login">
              <Button block={true} size={"large"} href={"/user/login"} style={{height:'40px'}}>返回登录</Button>
            </Link>
          </div>
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};
export default Register;
