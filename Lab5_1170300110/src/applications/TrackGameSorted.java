package applications;

import java.util.Collections;
import java.util.Comparator;

import physicalobject.Athlete;

public class TrackGameSorted extends TrackGame {

  // ��� tracks ��ż�� 6 1 2 3 4 5 6 ��������
  // 6/2 = 3 ����˳�� 3 4 2 5 1 6
  // ��� tracks ������5 1 2 3 4 5
  // 5/2 = 2 ����˳�� 3 2 4 1 5
  @Override
  void setPlayer() {
    Collections.sort(objects, new Comparator<Athlete>() {

      @Override
      public int compare(Athlete o1, Athlete o2) {
        int num1 = (int) (o1.getGrade() * 100);
        int num2 = (int) (o2.getGrade() * 100);

        return num1 - num2;
      }

    });
    // sort(objects, 0, objects.size() - 1);
    // Collections.reverse(objects);

    if (objects.size() <= numOfTracks) {
      if (numOfTracks % 2 == 0) {
        int counter = 0;
        int index = numOfTracks / 2;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + counter - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + counter - 1));
            // objects.get(counter).setTrack(tracks.get(index + counter - 1));
            counter++;
          }
          index--;
        }
      } else {
        int counter = 0;
        int index = numOfTracks / 2 + 1;
        addObjectToTrack(tracks.get(index - 1), objects.get(counter));
        rel2.put(objects.get(counter), tracks.get(index - 1));
        // objects.get(counter).setTrack(tracks.get(index - 1));
        index--;
        counter++;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + counter - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + counter - 1));
            // objects.get(counter).setTrack(tracks.get(index + counter - 1));
            counter++;
          }
          index--;
        }
      }
    } else {
      int numOfGroup;
      if (objects.size() % numOfTracks == 0) {
        numOfGroup = objects.size() / numOfTracks;
      } else {
        numOfGroup = objects.size() / numOfTracks + 1;
      }

      // �ȶ�������Ա����в���
      int cont = 1;
      int counter = 0;

      int f = 0; // ����
      while (cont <= numOfGroup - 1) {
        f = counter % numOfTracks;

        if (numOfTracks % 2 == 0) {

          int index = numOfTracks / 2;
          while (index >= 1) {

            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;

            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;

            index--;
          }
        } else {

          int index = numOfTracks / 2 + 1;
          addObjectToTrack(tracks.get(index - 1), objects.get(counter));
          rel2.put(objects.get(counter), tracks.get(index - 1));
          // objects.get(counter).setTrack(tracks.get(index - 1));
          index--;
          counter++;
          f++;
          while (index >= 1) {

            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;

            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;

            index--;
          }
        }
        cont++;
      }

      /// �������һ��

      if (numOfTracks % 2 == 0) {
        f = counter % numOfTracks;
        int index = numOfTracks / 2;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;
          }
          index--;
        }
      } else {
        f = counter % numOfTracks;
        int index = numOfTracks / 2 + 1;
        addObjectToTrack(tracks.get(index - 1), objects.get(counter));
        rel2.put(objects.get(counter), tracks.get(index - 1));
        // objects.get(counter).setTrack(tracks.get(index - 1));
        index--;
        counter++;
        f++;
        while (index >= 1) {
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index - 1));
            // objects.get(counter).setTrack(tracks.get(index - 1));
            counter++;
            f++;
          }
          if (counter < objects.size()) {
            addObjectToTrack(tracks.get(index + f - 1), objects.get(counter));
            rel2.put(objects.get(counter), tracks.get(index + f - 1));
            // objects.get(counter).setTrack(tracks.get(index + f - 1));
            counter++;
            f++;
          }
          index--;
        }
      }

    }
  }

}
