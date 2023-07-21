import { DrawerForm, ProColumns, ProFormInstance, ProFormText } from '@ant-design/pro-components';
import '@umijs/max';
import {Form, Input} from 'antd';
import React, { useEffect, useRef } from 'react';

export type Props = {
  values: API.UserVo;
  columns: ProColumns<API.UserVo>[];
  setVisible: (visible: boolean) => void;
  onSubmit: (values: API.UserVo) => Promise<void>;
  visible: boolean;
};

const UpdateModal: React.FC<Props> = (props) => {
  const { values, visible, setVisible, onSubmit } = props;
  const formRef = useRef<ProFormInstance>();

  useEffect(() => {
    if (formRef) {
      formRef.current?.setFieldsValue(values);
    }
  }, [values]);

  return (
    <DrawerForm<API.UserVo>
      onFinish={async (value) => {
        onSubmit?.(value);
      }}
      formRef={formRef}
      formKey="update-modal-form"
      autoFocusFirstInput
      onOpenChange={setVisible}
      title="修改用户"
      open={visible}
    >
      <ProFormText name="userName" label="昵称" initialValue={values.userName}
                   rules={[{ required: true, message: '昵称不可为空！' }]}/>
      <ProFormText name="userAvatar" label="头像URL" initialValue={values.userAvatar}
                   rules={[{ required: true, message: '头像URL不可为空！' }]}/>
      <Form.Item name="userProfile" label="简介">
        <Input.TextArea defaultValue={values.userProfile}  />
      </Form.Item>
      <ProFormText name="email" label="邮箱" initialValue={values.email}
                   rules={[{ required: true, message: '邮箱不可为空！' }]}/>
      <ProFormText name="qq" label="QQ" initialValue={values.qq}
                   rules={[{ required: true, message: 'QQ不可为空！' }]}/>
      <ProFormText name="userRole" label="角色" initialValue={values.userRole}
                   rules={[{ required: true, message: '角色不可为空！' }]}/>
    </DrawerForm>
  );
};
export default UpdateModal;
