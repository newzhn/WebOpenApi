import {
  ProColumns,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import {Modal} from 'antd';
import React from 'react';

export type props = {
  columns: ProColumns<API.InterfaceInfoVo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfoAddRequest) => void;
  visible: boolean;
};
const CreateModal: React.FC<props> = (props) => {
  const {visible, columns, onCancel, onSubmit} = props;

  return (
    <Modal visible={visible} footer={null} onCancel={() => onCancel?.()} width={700}>
      <ProTable
        type={"form"}
        columns={columns}
        onSubmit={async (value) => {
          // @ts-ignore
          onSubmit?.(value)
        }}
      />
    </Modal>
  );
};
export default CreateModal;
