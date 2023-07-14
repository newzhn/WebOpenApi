import {
  ProColumns, ProFormInstance,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Modal} from 'antd';
import React, {useEffect, useRef} from 'react';

export type props = {
  values: API.InterfaceInfoVo;
  columns: ProColumns<API.InterfaceInfoVo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfoUpdateRequest) => Promise<void>;
  visible: boolean;
};
const CreateModal: React.FC<props> = (props) => {
  const {values,visible, columns, onCancel, onSubmit} = props;

  const formRef = useRef<ProFormInstance>()

  useEffect(() => {
    if (formRef) {
      formRef.current?.setFieldsValue(values);
    }
  },[values])

  return (
    <Modal visible={visible} footer={null} onCancel={() => onCancel?.()} width={700}>
      <ProTable
        type={"form"}
        formRef={formRef}
        columns={columns}
        onSubmit={async (value) => {
          value.id = values.id
          value.status = values.status
          // @ts-ignore
          onSubmit?.(value)
        }}
      />
    </Modal>
  );
};
export default CreateModal;
