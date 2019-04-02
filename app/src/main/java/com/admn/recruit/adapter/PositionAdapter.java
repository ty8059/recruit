package com.admn.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.admn.recruit.R;
import com.admn.recruit.common.Constants;
import com.admn.recruit.model.Position;

import java.util.List;

public class PositionAdapter extends ArrayAdapter<Position> {

    public PositionAdapter(Context context, int resource, List<Position> objects) {
        super(context, resource, objects);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        Position record = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.position_item, null);
            viewHolder = new ViewHolder();
            viewHolder.positionName = convertView.findViewById(R.id.tv_position_name);
            viewHolder.positionSalary = convertView.findViewById(R.id.tv_position_salary);
            viewHolder.positionWorkType = convertView.findViewById(R.id.tv_position_work_type);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 设置textview
        if (record.getSalaryType().equals(Constants.SALARY_TYPE.MONTH)) {
            viewHolder.positionSalary.setText(String.valueOf(record.getPositionSalary()) + " / 月");
        } else {
            viewHolder.positionSalary.setText(String.valueOf(record.getPositionSalary()) + " / 日");
        }


        if (record.getPositionWorkType().equals(Constants.WORK_TYPE.FULL_TIME)) {
            viewHolder.positionWorkType.setText("全职");
        }
        else if (record.getPositionWorkType().equals(Constants.WORK_TYPE.PART_TIME)) {
            viewHolder.positionWorkType.setText("兼职");
        }
        else {
            viewHolder.positionWorkType.setText("实习");
        }

        viewHolder.positionName.setText(record.getPositionName());


        return convertView;
    }



    class ViewHolder {
        TextView positionName;
        TextView positionSalary;
        TextView positionWorkType;
    }


}


