import { DrawerForm, ProColumns, ProFormInstance, ProFormText } from '@ant-design/pro-components';
import '@umijs/max';
import { Form, Input } from 'antd';
import React, { useRef } from 'react';

export type Props = {
  columns: ProColumns<API.UserVo>[];
  setVisible: (visible: boolean) => void;
  onSubmit: (values: API.UserVo) => Promise<void>;
  visible: boolean;
};

const CreateModal: React.FC<Props> = (props) => {
  const { visible, setVisible, onSubmit } = props;
  const formRef = useRef<ProFormInstance>();

  return (
    <DrawerForm<API.UserVo>
      onFinish={async (value) => {
        console.log('---------->', value);
        onSubmit?.(value);
      }}
      formRef={formRef}
      formKey="update-modal-form"
      autoFocusFirstInput
      onOpenChange={setVisible}
      title="新增用户"
      open={visible}
    >
      <ProFormText name="userAccount" label="账户名" placeholder={'请输入账户名'}
                   rules={[{ required: true, message: '账户名不可为空！' }]}/>
      <ProFormText.Password
        label="密码"
        name="userPassword"
        placeholder={'请输入密码'}
        rules={[
          {
            required: true,
            message: '密码是必填项！',
          },
        ]}
      />
      <Form.Item name="userProfile" label="简介">
        <Input.TextArea />
      </Form.Item>
      <ProFormText name="email" label="邮箱" placeholder={'请输入邮箱'}
                   rules={[{ required: true, message: '邮箱不可为空！' }]}/>
      <ProFormText name="qq" label="QQ" placeholder={'请输入QQ'}
                   rules={[{ required: true, message: 'QQ不可为空！' }]}/>
      <ProFormText name="userRole" label="角色" placeholder={'请输入角色'}
                   rules={[{ required: true, message: '角色不可为空！' }]}/>
    </DrawerForm>
  );
};
export default CreateModal;
