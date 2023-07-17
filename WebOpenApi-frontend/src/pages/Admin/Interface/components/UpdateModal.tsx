import { DrawerForm, ProColumns, ProFormInstance, ProFormText } from '@ant-design/pro-components';
import { EditableProTable } from '@ant-design/pro-table';
import '@umijs/max';
import { Form, Input } from 'antd';
import React, { useEffect, useRef, useState } from 'react';

export type FormValueType = {
  target?: string;
  template?: string;
  type?: string;
  time?: string;
  frequency?: string;
} & Partial<API.RuleListItem>;

export type Props = {
  values: API.InterfaceInfoVo;
  columns: ProColumns<API.InterfaceInfoVo>[];
  setVisible: (visible: boolean) => void;
  onSubmit: (values: API.InterfaceInfoVo) => Promise<void>;
  visible: boolean;
  requestColumns: ProColumns<API.RequestParamsRemarkVO>[];
  responseColumns: ProColumns<API.RequestParamsRemarkVO>[];
};

const UpdateModal: React.FC<Props> = (props) => {
  const { values, visible, setVisible, onSubmit, requestColumns, responseColumns } = props;
  const formRef = useRef<ProFormInstance>();
  // @ts-ignore
  const [requestEditableKeys, setRequestEditableKeys] = useState<React.Key[]>(() => {
    return values.requestParamsRemark?.map((item) => item.id) || [];
  });
  const [requestDataSource, setRequestDataSource] = useState<
    readonly API.RequestParamsRemarkVO[]
    >(() => values.requestParamsRemark || []);
  // @ts-ignore
  const [responseEditableKeys, setResponseEditableKeys] = useState<React.Key[]>(() => {
    return values.responseParamsRemark?.map((item) => item.id) || [];
  });
  const [responseDataSource, setResponseDataSource] = useState<
    readonly API.ResponseParamsRemarkVo[]
    >(() => values.responseParamsRemark || []);

  useEffect(() => {
    if (formRef) {
      let requestIds =
        values.requestParamsRemark?.map((item) => item.id as unknown as string) || [];
      setRequestEditableKeys(requestIds);
      setRequestDataSource(values.requestParamsRemark || []);

      let responseIds =
        values.responseParamsRemark?.map((item) => item.id as unknown as string) || [];
      setResponseEditableKeys(responseIds);
      setResponseDataSource(values.responseParamsRemark || []);
      formRef.current?.setFieldsValue(values);
    }
  }, [values]);

  return (
    <DrawerForm<API.InterfaceInfoVo>
      onFinish={async (value) => {
        onSubmit?.(value);
      }}
      formRef={formRef}
      formKey="update-modal-form"
      autoFocusFirstInput
      onOpenChange={setVisible}
      title="修改接口"
      open={visible}
    >
      <ProFormText
        name="name"
        label="接口名称"
        initialValue={values.name}
        rules={[{ required: true, message: '接口名称不可为空！' }]}
      />

      <ProFormText
        name="description"
        label="描述"
        initialValue={values.description}
        rules={[{ required: true, message: '描述不可为空！' }]}
      />
      <ProFormText
        name="method"
        label="请求方法"
        initialValue={values.method}
        rules={[{ required: true, message: '请求方法不可为空！' }]}
      />

      <ProFormText
        name="host"
        label="接口地址"
        initialValue={values.host}
        rules={[{ required: true, message: '接口地址不可为空！' }]}
      />
      <ProFormText
        name="uri"
        label="接口路径"
        initialValue={values.uri}
        rules={[{ required: true, message: '接口路径不可为空！' }]}
      />
      <ProFormText
        name="applyNum"
        label="接口申请次数"
        initialValue={values.applyNum}
        rules={[{ required: true, message: '接口申请次数不可为空！' }]}
      />
      <Form.Item name="requestParams" label="请求参数示例">
        <Input.TextArea defaultValue={values.requestParams} />
      </Form.Item>
      <Form.Item name="requestParamsRemark" label="请求参数说明">
        <EditableProTable<API.RequestParamsRemarkVO>
          rowKey="id"
          toolBarRender={false}
          columns={requestColumns}
          value={requestDataSource}
          onChange={setRequestDataSource}
          recordCreatorProps={{
            newRecordType: 'dataSource',
            position: 'bottom',
            record: () => ({
              id: Date.now(),
              isRequired: 'no',
              type: 'string',
            }),
          }}
          editable={{
            type: 'multiple',
            editableKeys: requestEditableKeys,
            onChange: setRequestEditableKeys,
            actionRender: (row, _, dom) => {
              return [dom.delete];
            },
            onValuesChange: (record, recordList) => {
              setRequestDataSource(recordList);
              formRef.current?.setFieldsValue({
                requestParamsRemark: recordList,
              });
            },
          }}
        />
      </Form.Item>

      <Form.Item name="responseParamsRemark" label="响应参数说明">
        <EditableProTable<API.ResponseParamsRemarkVo>
          rowKey="id"
          toolBarRender={false}
          columns={responseColumns}
          value={responseDataSource}
          onChange={setResponseDataSource}
          recordCreatorProps={{
            newRecordType: 'dataSource',
            position: 'bottom',
            record: () => ({
              id: Date.now(),
              type: 'string',
            }),
          }}
          editable={{
            type: 'multiple',
            editableKeys: responseEditableKeys,
            onChange: setResponseEditableKeys,
            actionRender: (row, _, dom) => {
              return [dom.delete];
            },
            onValuesChange: (record, recordList) => {
              setResponseDataSource(recordList);
              formRef.current?.setFieldsValue({
                responseParamsRemark: recordList,
              });
            },
          }}
        />
      </Form.Item>
      <Form.Item name="requestHeader" label="请求头">
        <Input.TextArea defaultValue={values.requestHeader} />
      </Form.Item>
      <Form.Item name="responseHeader" label="响应头">
        <Input.TextArea defaultValue={values.responseHeader} />
      </Form.Item>
    </DrawerForm>
  );
};
export default UpdateModal;
